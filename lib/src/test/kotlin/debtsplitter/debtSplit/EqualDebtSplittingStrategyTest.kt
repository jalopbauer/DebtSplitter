package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EqualDebtSplittingStrategyTest {
    @Test
    fun shouldSplitPaymentEquallyBetweenParties() {
        val ownedParty = Party("1")
        val amount = 10.0
        val payment = Payment(ownedParty, amount)
        val partyDebt = PartyDebt(mapOf(Party("2") to 5.0, Party("3") to 5.0))
        val parties = partyDebt.partyDebt.keys.toList()
        val debtSplitResult = DebtSplittingStrategy.splitEqually(payment, parties)
        assertResultIsBalancedDebtSplit(debtSplitResult)
        assertPayment(payment, debtSplitResult)
        assertPartyDebt(partyDebt, debtSplitResult)
        assertPaymentAmountIsSplitAccordingly(payment, debtSplitResult)
    }

    private fun assertPaymentAmountIsSplitAccordingly(payment: Payment, debtSplitResult: DebtSplit) {
        assertEquals(payment.amount, debtSplitResult.partyDebt().amount())
    }

    private fun assertPartyDebt(
        expectedPartyDebt: PartyDebt,
        debtSplitResult: DebtSplit
    ) {
        assertEquals(expectedPartyDebt, debtSplitResult.partyDebt())
    }

    private fun assertPayment(expectedPayment: Payment, debtSplitResult: DebtSplit) {
        assertAmountOwned(expectedPayment.amount, debtSplitResult)
        assertOwnedParty(expectedPayment.ownedParty, debtSplitResult)
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