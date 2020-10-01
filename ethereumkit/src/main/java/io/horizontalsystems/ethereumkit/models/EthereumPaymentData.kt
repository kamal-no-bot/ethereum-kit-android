package io.horizontalsystems.ethereumkit.models

import java.math.BigInteger

data class EthereumPaymentData(
        val address: String,
        val value: BigInteger? = null,
        val gas: Long? = null,
        val gasPrice: Long? = null,
        val parameters: MutableMap<String, String>? = null) {

    val uriPaymentAddress: String
        get() {
            var uriAddress = address
            val params = mutableMapOf<String, String>()
            value?.let {
                params["value"] = it.toString()
            }
            gas?.let {
                params["gas"] = it.toString()
            }
            gasPrice?.let {
                params["gasPrice"] = it.toString()
            }
            parameters?.let {
                for ((name, value) in it) {
                    params[name] = value
                }
            }

            if (params.isNotEmpty()) {
                val paramsString = params.map { "${it.key}=${it.value}" }
                uriAddress += "?$paramsString"
            }

            return uriAddress
        }
}