package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt

data object DebtSplittingStrategy {
    fun splitEqually(payment: Payment, borrowedParties: List<Party>): DebtSplit {
        val amountPerBorrowedParty = payment.amount / borrowedParties.size
        val partyDebt = borrowedParties.associateWith { amountPerBorrowedParty }
        return BalancedDebtSplit(payment.ownedParty, PartyDebt(partyDebt))
    }
}
