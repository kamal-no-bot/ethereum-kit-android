package io.horizontalsystems.ethereumkit.core

import io.horizontalsystems.ethereumkit.utils.PaymentAddressParser
import org.junit.Test
import java.math.BigInteger
import kotlin.test.*

class PaymentParserTest {

    @Test
    fun testValidateEthereumPayment() {
        val parser = PaymentAddressParser(false)
        val data = parser.parse("ethereum:0xD8869d9E3d497323561Fbca2319a9FC3F6f10c4B?value=3.24562e18&gas=55000&gasPrice=89000000000")
        assertEquals(data.address, "0xD8869d9E3d497323561Fbca2319a9FC3F6f10c4B")
        assertEquals(data.value, BigInteger("24562e18"))
        assertEquals(data.gas, 55000L)
        assertEquals(data.gasPrice, 89000000000L)
    }
}