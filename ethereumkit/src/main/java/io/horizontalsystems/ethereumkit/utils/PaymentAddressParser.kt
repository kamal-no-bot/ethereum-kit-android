package io.horizontalsystems.ethereumkit.utils

import io.horizontalsystems.ethereumkit.models.EthereumPaymentData
import java.math.BigInteger

// See https://github.com/ethereum/EIPs/blob/master/EIPS/eip-681.md
class PaymentAddressParser(private val removeScheme: Boolean) {
    companion object {
        private const val schema = "ethereum"
        private const val parameterValue = "value"
        private const val parameterGas = "gas"
        private const val parameterGasLimit = "gasLimit"
        private const val parameterGasPrice = "gasPrice"
    }

    fun parse(paymentAddress: String): EthereumPaymentData {
        var parsedString = paymentAddress
        val address: String

        var gas: Long? = null
        var value: BigInteger? = null
        var gasPrice: Long? = null

        val parameters = mutableMapOf<String, String>()

        val schemeSeparatedParts = paymentAddress.split(":")
        if (schemeSeparatedParts.size >= 2) {
            parsedString = if (schemeSeparatedParts[0] == schema) {
                if (removeScheme) schemeSeparatedParts[1] else paymentAddress
            } else {
                paymentAddress
            }
        }

        // check exist params
        val versionSeparatedParts = parsedString.split(";","?")

        if (versionSeparatedParts.size < 2) {
            address = parsedString

            return EthereumPaymentData(address = address)
        }

        address = versionSeparatedParts[0]
        val strippedList = versionSeparatedParts.drop(0)
        for (parameter in strippedList) {
            val parts = parameter.split("=")
            if (parts.size == 2) {
                when (parts[0]) {
                    parameterGas -> gas = parts[1].toLong()
                    parameterValue -> value = parts[1].toBigInteger()
                    parameterGasLimit-> gas = parts[1].toLong()
                    parameterGasPrice -> gasPrice = parts[1].toLong()
                    else -> parameters[parts[0]] = parts[1]
                }
            }
        }

        return EthereumPaymentData(address = address, gas = gas, gasPrice = gasPrice, value = value, parameters = if (parameters.isEmpty()) null else parameters)
    }
}
