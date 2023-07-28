package debtsplitter.debtSplit

import debtsplitter.amount.MoneyAmount
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
                "Testing splitting number by ",
                Payment(Party("1"), MoneyAmount(10.0)),
                PartyDebt(
                    mapOf(
                        Party("2") to MoneyAmount(5.0),
                        Party("3") to MoneyAmount(5.0)
                    )
                )
            ),
            DebtSplittingStrategyTestInput(
                "Testing number two",
                Payment(Party("1"), MoneyAmount(10.0)),
                PartyDebt(
                    mapOf(
                        Party("2") to MoneyAmount(3.34),
                        Party("3") to MoneyAmount(3.33),
                        Party("4") to MoneyAmount(3.33),
                    )
                )
            ),
            DebtSplittingStrategyTestInput(
                "Testing number three",
                Payment(Party("1"), MoneyAmount(10.0)),
                PartyDebt(
                    mapOf(
                        Party("2") to MoneyAmount( 1.67),
                        Party("3") to MoneyAmount( 1.67),
                        Party("4") to MoneyAmount( 1.67),
                        Party("5") to MoneyAmount( 1.67),
                        Party("6") to MoneyAmount( 1.66),
                        Party("7") to MoneyAmount( 1.66)
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
        assertAmountOwned(expectedPayment.amount.amount, debtSplitResult)
        assertOwnedParty(expectedPayment.ownedParty, debtSplitResult)
    }

    private fun assertOwnedParty(expectedOwnedParty: Party, debtSplitResult: DebtSplit) {
        assertEquals(expectedOwnedParty, debtSplitResult.ownedParty())
    }

    private fun assertAmountOwned(expectedAmount: Double, debtSplit: DebtSplit) {
        val actualAmountOwned = debtSplit.amountOwned()
        assertEquals(expectedAmount, actualAmountOwned.amount)
    }

    private fun assertResultIsBalancedDebtSplit(result: DebtSplit) {
        assert(result is BalancedDebtSplit)
    }
}