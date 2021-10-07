package io.horizontalsystems.ethereumkit.contracts

import io.horizontalsystems.ethereumkit.crypto.CryptoUtils
import io.horizontalsystems.ethereumkit.models.Address
import io.horizontalsystems.ethereumkit.spv.core.toBigInteger
import io.horizontalsystems.ethereumkit.spv.core.toInt
import java.math.BigInteger
import kotlin.math.max

object ContractMethodHelper {
    fun getMethodId(methodSignature: String): ByteArray {
        return CryptoUtils.sha3(methodSignature.toByteArray()).copyOfRange(0, 4)
    }

    fun encodedABI(methodId: ByteArray, arguments: List<Any>): ByteArray {
        var data = methodId
        var arraysData = byteArrayOf()
        arguments.forEach { argument ->
            when (argument) {
                is BigInteger -> {
                    data += pad(argument.toByteArray())
                }
                is Address -> {
                    data += pad(argument.raw)
                }
                is List<*> -> {
                    val addresses = argument.filterIsInstance<Address>()

                    data += pad(BigInteger.valueOf(arguments.size * 32L + arraysData.size).toByteArray())
                    arraysData += encode(addresses)
                }
                is ByteArray -> {
                    data += pad(BigInteger.valueOf(arguments.size * 32L + arraysData.size).toByteArray())
                    arraysData += pad(BigInteger.valueOf(data.size.toLong()).toByteArray()) + data
                }
            }
        }
        return data + arraysData
    }

    data class StructParameter(val argumentTypes: List<Any>)

    fun decodeABI(inputArguments: ByteArray, argumentTypes: List<Any>): List<Any> {
        var position = 0
        val parsedArguments = mutableListOf<Any>()
        argumentTypes.forEach { type ->
            when (type) {
                BigInteger::class -> {
                    parsedArguments.add(inputArguments.copyOfRange(position, position + 32).toBigInteger())
                }
                Address::class -> {
                    parsedArguments.add(parseAddress(inputArguments.copyOfRange(position, position + 32)))
                }
                List::class -> {
                    val arrayPosition = inputArguments.copyOfRange(position, position + 32).toInt()
                    val array = parseAddressArray(arrayPosition, inputArguments)
                    parsedArguments.add(array)
                }
                ByteArray::class -> {
                    val arrayPosition = inputArguments.copyOfRange(position, position + 32).toInt()
                    val byteArray: ByteArray = parseByteArray(arrayPosition, inputArguments)
                    parsedArguments.add(byteArray)
                }
                Bytes32Array::class -> {
                    val arrayPosition = inputArguments.copyOfRange(position, position + 32).toInt()
                    val bytes32Array = parseBytes32Array(arrayPosition, inputArguments)
                    parsedArguments.add(bytes32Array)
                }
                is StructParameter -> {
                    val argumentsPosition = inputArguments.copyOfRange(position, position + 32).toInt()
                    val structParameterData = inputArguments.copyOfRange(argumentsPosition, inputArguments.size)
                    val structParameter = decodeABI(structParameterData, type.argumentTypes)
                    parsedArguments.add(structParameter)
                }
            }
            position += 32
        }

        return parsedArguments
    }

    private fun parseBytes32Array(startPosition: Int, inputArguments: ByteArray): Bytes32Array {
        val dataStartPosition = startPosition + 32
        val size = inputArguments.copyOfRange(startPosition, dataStartPosition).toInt()
        val array: Array<ByteArray> = Array(size) { byteArrayOf() }

        for (i in 0 until size) {
            array[i] = inputArguments.copyOfRange(dataStartPosition + 32 * i, dataStartPosition + 32 * (i + 1))
        }

        return Bytes32Array(array)
    }

    private fun parseAddress(address: ByteArray): Address {
        return Address(address.copyOfRange(address.size - 20, address.size))
    }

    private fun parseByteArray(startPosition: Int, inputArguments: ByteArray): ByteArray {
        val dataStartPosition = startPosition + 32
        val size = inputArguments.copyOfRange(startPosition, dataStartPosition).toInt()
        return inputArguments.copyOfRange(dataStartPosition, dataStartPosition + size)
    }

    private fun parseAddressArray(positionStart: Int, inputArguments: ByteArray): List<Address> {
        val sizePositionEnd = positionStart + 32
        val arraySize = inputArguments.copyOfRange(positionStart, sizePositionEnd).toInt()
        val addressArray = mutableListOf<Address>()
        for (address in inputArguments.copyOfRange(sizePositionEnd, sizePositionEnd + arraySize * 32).toList().chunked(32)) {
            addressArray.add(parseAddress(address.toByteArray()))
        }
        return addressArray
    }

    private fun pad(data: ByteArray): ByteArray {
        val prePadding = ByteArray(max(0, 32 - data.size))
        return prePadding + data
    }

    private fun encode(array: List<Address>): ByteArray {
        var data = pad(BigInteger.valueOf(array.size.toLong()).toByteArray())
        for (address in array) {
            data += pad(address.raw)
        }
        return data
    }
}