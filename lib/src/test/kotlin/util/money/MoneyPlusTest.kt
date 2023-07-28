package util.money

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoneyPlusTest {
    @Test
    fun remainderIsFourWholeCarry() {
        val pair: Pair<Pair<Pair<ULong, UByte>, Pair<ULong, UByte>>, Pair<ULong, UByte>> = Pair(
            // 0.99 + 0.90 = 1,89
            Pair(Pair(0u, 99u), Pair(0u, 90u)), Pair(1u, 89u)
        )
        sumTest(pair)
    }
    private fun sumTest(pair: Pair<Pair<Pair<ULong, UByte>, Pair<ULong, UByte>>, Pair<ULong, UByte>>) {
        val (input, output) = pair
        val (leftSummand, rightSummand) = input
        val (leftSummandWhole, leftSummandDecimal) = leftSummand
        val (rightSummandWhole, rightSummandDecimal) = rightSummand
        val (wholeResult, decimalResult) = output
        val plus = Money(leftSummandWhole, leftSummandDecimal).plus(Money(rightSummandWhole, rightSummandDecimal))
        assertEquals(Money(wholeResult, decimalResult), plus)
    }
}