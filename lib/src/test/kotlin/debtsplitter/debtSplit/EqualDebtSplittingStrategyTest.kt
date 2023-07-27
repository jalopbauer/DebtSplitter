package debtsplitter.debtSplit

import debtsplitter.debt.Debt
import debtsplitter.party.Party
import org.junit.jupiter.api.Test

class EqualDebtSplittingStrategyTest {
    @Test
    fun shouldSplitDebtEquallyBetweenParties() {
        val ownedParty = Party("1")
        val amount = 10.0
        val debt = Debt(ownedParty, amount)
        val borrowedParties = listOf(ownedParty, Party("2"))
        val result = DebtSplittingStrategy.splitEqually(debt, borrowedParties)
        assertResultIsBalancedDebtSplit(result)
    }

    private fun assertResultIsBalancedDebtSplit(result: DebtSplit) {
        assert(result is BalancedDebtSplit)
    }
}