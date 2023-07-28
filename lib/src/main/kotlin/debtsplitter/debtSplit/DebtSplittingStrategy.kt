package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt
import util.money.Money

data object DebtSplittingStrategy {
    fun splitEqually(payment: Payment, borrowedParties: List<Party>): DebtSplittingStrategyResponse {
        val (amountPerBorrowedParty, restPerBorrowedParty) = payment.amount.div(borrowedParties.size.toULong())
        val valuesOfEachParty = valuesOfEachParty(restPerBorrowedParty, amountPerBorrowedParty, borrowedParties)
        val partyDebt = borrowedParties.zip(valuesOfEachParty).toMap()
        return BalancedDebtSplit(payment.ownedParty, PartyDebt(partyDebt))
    }

    private fun valuesOfEachParty(
        restPerBorrowedParty: ULong,
        amountPerBorrowedParty: Money,
        borrowedParties: List<Party>
    ) = (0 until restPerBorrowedParty.toLong()).map {amountPerBorrowedParty.plus(Money(0u,1u)) } +
            (restPerBorrowedParty until borrowedParties.size.toULong()).map { amountPerBorrowedParty }
}
