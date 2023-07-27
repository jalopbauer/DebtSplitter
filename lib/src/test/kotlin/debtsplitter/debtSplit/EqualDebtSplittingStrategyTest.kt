package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EqualDebtSplittingStrategyTest {
    @Test
    fun shouldSplitPaymentEquallyBetweenParties() {
        val ownedParty = Party("1")
        val amount = 10.0
        val payment = Payment(ownedParty, amount)
        val partyDebt = mapOf(Party("2") to 5.0, Party("3") to 5.0)
        val parties = partyDebt.keys.toList()
        val debtSplitResult = DebtSplittingStrategy.splitEqually(payment, parties)
        assertResultIsBalancedDebtSplit(debtSplitResult)
        assertPayment(payment, debtSplitResult)
        assertPartyDebt(partyDebt, debtSplitResult)
    }

    private fun assertPartyDebt(
        partyDebt: Map<Party, Double>,
        debtSplitResult: DebtSplit
    ) {
        assertEquals(partyDebt, debtSplitResult.partyDebt())
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