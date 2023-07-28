package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt
import org.junit.jupiter.api.Test
import util.money.Money
import kotlin.test.assertEquals

class EqualDebtSplittingStrategyTest {
    @Test
    fun shouldSplitPaymentEquallyBetweenParties() {
        val debtSplittingStrategyTestInputs = listOf(
            DebtSplittingStrategyTestInput(
                "Testing splitting number by ",
                Payment(Party("1"), Money.fromDouble(10.0)),
                PartyDebt(
                    mapOf(
                        Party("2") to Money.fromDouble(5.0),
                        Party("3") to Money.fromDouble(5.0)
                    )
                )
            ),
            DebtSplittingStrategyTestInput(
                "Testing number two",
                Payment(Party("1"), Money.fromDouble(10.0)),
                PartyDebt(
                    mapOf(
                        Party("2") to Money.fromDouble(3.34),
                        Party("3") to Money.fromDouble(3.33),
                        Party("4") to Money.fromDouble(3.33),
                    )
                )
            ),
            DebtSplittingStrategyTestInput(
                "Testing number three",
                Payment(Party("1"), Money.fromDouble(10.0)),
                PartyDebt(
                    mapOf(
                        Party("2") to Money.fromDouble( 1.67),
                        Party("3") to Money.fromDouble( 1.67),
                        Party("4") to Money.fromDouble( 1.67),
                        Party("5") to Money.fromDouble( 1.67),
                        Party("6") to Money.fromDouble( 1.66),
                        Party("7") to Money.fromDouble( 1.66)
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
        when (debtSplitResult) {
            is DebtSplit -> {
                assertPayment(payment, debtSplitResult)
                assertPartyDebt(partyDebt, debtSplitResult)
            }
        }
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

    private fun assertAmountOwned(expectedAmount: Money, debtSplit: DebtSplit) {
        val actualAmountOwned = debtSplit.amountOwned()
        assertEquals(expectedAmount, actualAmountOwned)
    }

    private fun assertResultIsBalancedDebtSplit(result: DebtSplittingStrategyResponse) {
        assert(result is BalancedDebtSplit)
    }
}