package util.money

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoneyDivTest {


    // 2.0 / 2 = 1.0 0
    @Test
    fun wholeNoDecimalNoCarry() {
        val pair = Pair<Triple<ULong, UByte, ULong>, Triple<ULong, UByte, ULong>>(
            // 2.0 / 2 = 1.0 0
            Triple(2u, 0u, 2u), Triple(1u, 0u, 0u)
        )
        divTest(pair)
    }

    @Test
    fun noWholeDecimalNoCarry() {
        val pair = Pair<Triple<ULong, UByte, ULong>, Triple<ULong, UByte, ULong>>(
            // 0.2 / 2 = 0.1 0
            Triple(0u, 2u, 2u), Triple(0u, 1u, 0u)
        )
        divTest(pair)
    }

    @Test
    fun wholeDecimalNoCarry() {
        val pair = Pair<Triple<ULong, UByte, ULong>, Triple<ULong, UByte, ULong>>(
            // 2.2 / 2 = 1.1 0
            Triple(2u, 2u, 2u), Triple(1u, 1u, 0u)
        )
        divTest(pair)
    }

    private fun divTest(pair: Pair<Triple<ULong, UByte, ULong>, Triple<ULong, UByte, ULong>>) {
        val (input, output) = pair
        val (whole, decimal, n) = input
        val (wholeResult, decimalResult, remResult) = output
        val div = Money(whole, decimal).div(n)
        assertEquals(Money(wholeResult, decimalResult), div.first)
        assertEquals(remResult, div.second)
    }
}