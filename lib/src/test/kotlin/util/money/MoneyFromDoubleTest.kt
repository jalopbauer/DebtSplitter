package util.money

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoneyFromDoubleTest {
    @Test
    fun validNumber() {
        val pair: Pair<Double, Pair<ULong, UByte>> = Pair(1.89, Pair(1u, 89u))
        sumTest(pair)
    }

    @Test
    fun validNumberWithExtra() {
        val pair: Pair<Double, Pair<ULong, UByte>> = Pair(1.899, Pair(1u, 89u))
        sumTest(pair)
    }

    private fun sumTest(pair: Pair<Double, Pair<ULong, UByte>>) {
        val (double, output) = pair
        val (wholeResult, decimalResult) = output
        val plus = Money.fromDouble(double)
        assertEquals(Money(wholeResult, decimalResult), plus)
    }
}