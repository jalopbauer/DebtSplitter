package util.money

data class Money(val whole: ULong, val decimal: UByte) {
    fun div(n: ULong): Pair<Money, ULong>  {
        val wholeDiv = whole.floorDiv(n)
        val wholeRem = decimal.plus(whole.rem(n).times(100u))
        val decimalDiv = wholeRem.floorDiv(n).toUByte()
        val decimalRem = wholeRem.rem(n)
        return Pair(Money(wholeDiv, decimalDiv), decimalRem)
    }

    fun plus(money: Money) : Money {
        val decimalsSummed = decimal.plus(money.decimal).toUByte()
        val (decimalRes, carry) = decimalsSummed.takeIf { it >= 100u }
            ?.let {
                Pair(it.minus(100u).toUByte(), 1u)
            } ?: Pair(decimalsSummed, 0u)
        return Money(whole.plus(money.whole).plus(carry), decimalRes)
    }

    companion object {
        fun fromDouble(double: Double): Money {
            val doubleString = double.toString()
            val (valuesBeforeDot, valuesAfterDot) = doubleString
                .fold(Triple("", "", false)) {
                        (valuesBeforeDot, valuesAfterDot, hasDotAppeared), c ->
                    when (c) {
                        '.' -> Triple(valuesBeforeDot, valuesAfterDot, true)
                        else ->
                            if (hasDotAppeared) {
                                if (valuesAfterDot.length >= 2) {
                                    Triple(valuesBeforeDot, valuesAfterDot, hasDotAppeared)
                                } else {
                                    Triple(valuesBeforeDot, valuesAfterDot + c, hasDotAppeared)
                                }
                            } else{
                                Triple(valuesBeforeDot + c, valuesAfterDot, hasDotAppeared)
                            }
                    }

                }
            return Money(valuesBeforeDot.toULong(), valuesAfterDot.toUByte())
        }
    }
}
