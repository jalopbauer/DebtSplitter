package util.money

data class Money(private val whole: ULong, val decimal: UByte) {
    fun div(n: ULong): Pair<Money, ULong>  {
        val wholeDiv = whole.floorDiv(n)
        val wholeRem = decimal.plus(whole.rem(n).times(10u))
        val decimalDiv = wholeRem.floorDiv(n).toUByte()
        val decimalRem = wholeRem.rem(n)
        return Pair(Money(wholeDiv, decimalDiv), decimalRem)
    }
}
