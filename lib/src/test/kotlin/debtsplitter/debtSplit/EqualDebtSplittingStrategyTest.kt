package debtsplitter.debtSplit

import debtsplitter.debt.Payment
import debtsplitter.party.Party
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EqualDebtSplittingStrategyTest {
    @Test
    fun shouldSplitPaymentEquallyBetweenParties() {
        val ownedParty = Party("1")
        val amount = 10.0
        val payment = Payment(ownedParty, amount)
        val borrowedParties = listOf(ownedParty, Party("2"))
        val debtSplitResult = DebtSplittingStrategy.splitEqually(payment, borrowedParties)
        assertResultIsBalancedDebtSplit(debtSplitResult)
        assertPayment(payment, debtSplitResult)
    }

    private fun assertPayment(payment: Payment, debtSplitResult: DebtSplit) {
        assertAmountOwned(payment.amount, debtSplitResult)
        assertOwnedParty(payment.ownedParty, debtSplitResult)
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