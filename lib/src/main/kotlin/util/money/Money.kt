package util.money

data class Money(private val whole: ULong, val decimal: UByte) {
    fun div(n: ULong): Pair<Money, ULong>  {
        val wholeDiv = whole.floorDiv(n)
        val wholeRem = decimal.plus(whole.rem(n).times(100u))
        val decimalDiv = wholeRem.floorDiv(n).toUByte()
        val decimalRem = wholeRem.rem(n)
        return Pair(Money(wholeDiv, decimalDiv), decimalRem)
    }

    fun fromDouble(double: Double): Money {
        val doubleString = double.toString()
        val (valuesBeforeDot, valuesAfterDot) = doubleString
            .fold(Triple("", "", false)) {
                    (valuesBeforeDot, valuesAfterDot, hasDotAppeared), c ->
                when (c) {
                    '.' -> Triple(valuesBeforeDot, valuesAfterDot, true)
                    else ->
                        if (hasDotAppeared) {
                            if (valuesBeforeDot.length >= 2) {
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
