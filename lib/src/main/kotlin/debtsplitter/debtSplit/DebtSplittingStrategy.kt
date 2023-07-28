package debtsplitter.debtSplit

import debtsplitter.amount.MoneyAmount
import debtsplitter.payment.Payment
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt

data object DebtSplittingStrategy {
    fun splitEqually(payment: Payment, borrowedParties: List<Party>): DebtSplit {
        val amountPerBorrowedParty = MoneyAmount(payment.amount.amount / borrowedParties.size).amount
        val restPerBorrowedParty = (payment.amount.amount % borrowedParties.size).toInt()
        val valuesOfEachParty = valuesOfEachParty(restPerBorrowedParty, amountPerBorrowedParty, borrowedParties)
        val partyDebt = borrowedParties.zip(valuesOfEachParty).toMap()
        return BalancedDebtSplit(payment.ownedParty, PartyDebt(partyDebt))
    }

    private fun valuesOfEachParty(
        restPerBorrowedParty: Int,
        amountPerBorrowedParty: Double,
        borrowedParties: List<Party>
    ) = (0 until restPerBorrowedParty).map { MoneyAmount(amountPerBorrowedParty + 0.01) } +
            (restPerBorrowedParty until borrowedParties.size).map { MoneyAmount(amountPerBorrowedParty) }
}
