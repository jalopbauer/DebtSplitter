package debtsplitter.amount

data class MoneyAmount(private val double: Double) {
    val amount : Double
    init {
        val doubleString = double.toString()
        val (valuesBeforeDot, valuesAfterDot) = doubleString
            .fold(Triple("", "", false)) {
                (valuesBeforeDot, valuesAfterDot, hasDotAppeared), c ->
                    when (c) {
                        '.' -> Triple(valuesBeforeDot, valuesAfterDot, true)
                        else ->
                            if (hasDotAppeared) {
                                Triple(valuesBeforeDot, valuesAfterDot + c, hasDotAppeared)
                            } else{
                                Triple(valuesBeforeDot + c, valuesAfterDot, hasDotAppeared)
                            }
                    }

        }
        val finalString = "$valuesBeforeDot.${valuesAfterDot.take(2)}"
        amount = finalString.toDouble()
    }
}
