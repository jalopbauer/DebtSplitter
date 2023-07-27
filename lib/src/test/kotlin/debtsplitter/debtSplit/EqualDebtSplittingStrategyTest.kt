package debtsplitter.debtSplit

import debtsplitter.debt.Debt
import debtsplitter.party.Party
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EqualDebtSplittingStrategyTest {
    @Test
    fun shouldSplitDebtEquallyBetweenParties() {
        val ownedParty = Party("1")
        val amount = 10.0
        val debt = Debt(ownedParty, amount)
        val borrowedParties = listOf(ownedParty, Party("2"))
        val debtSplitResult = DebtSplittingStrategy.splitEqually(debt, borrowedParties)
        assertResultIsBalancedDebtSplit(debtSplitResult)
        assertDebt(debt, debtSplitResult)
    }

    private fun assertDebt(debt: Debt, debtSplitResult: DebtSplit) {
        assertAmountOwned(debt.amount, debtSplitResult)
        assertOwnedParty(debt.ownedParty, debtSplitResult)
    }

    private fun assertOwnedParty(expectedOwnedParty: Party, debtSplitResult: DebtSplit) {
        assertEquals(expectedOwnedParty, debtSplitResult.ownedParty())
    }

    private fun assertAmountOwned(expectedAmount: Double, debtSplit: DebtSplit) {
        val actualAmountOwned = debtSplit.amountOwned()
        assertEquals(expectedAmount, actualAmountOwned)
    }

    private fun assertResultIsBalancedDebtSplit(result: DebtSplit) {
        assert(result is BalancedDebtSplit)
    }
}