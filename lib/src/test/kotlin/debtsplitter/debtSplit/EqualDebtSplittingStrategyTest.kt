package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EqualDebtSplittingStrategyTest {
    @Test
    fun shouldSplitPaymentEquallyBetweenParties() {
        val debtSplittingStrategyTestInputs = listOf(
            DebtSplittingStrategyTestInput(
                "Testing number juan",
                Payment(Party("1"), 10.0),
                PartyDebt(
                    mapOf(
                        Party("2") to 5.0,
                        Party("3") to 5.0
                    )
                )
            )
        )
        debtSplittingStrategyTestInputs.forEach { debtSplittingStrategyTestInput ->
            debtSplittingTest(debtSplittingStrategyTestInput)
        }
    }

    private fun debtSplittingTest(debtSplittingStrategyTestInput: DebtSplittingStrategyTestInput) {
        val (testName, payment, partyDebt, parties) = debtSplittingStrategyTestInput
        println(testName)
        val debtSplitResult = debtSplit(payment, parties)
        assertResultIsBalancedDebtSplit(debtSplitResult)
        assertPayment(payment, debtSplitResult)
        assertPartyDebt(partyDebt, debtSplitResult)
    }

    private fun debtSplit(
        payment: Payment,
        parties: List<Party>
    ) = DebtSplittingStrategy.splitEqually(payment, parties)

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