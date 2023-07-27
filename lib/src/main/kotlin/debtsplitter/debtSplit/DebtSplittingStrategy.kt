package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party

data object DebtSplittingStrategy {
    fun splitEqually(payment: Payment, borrowedParties: List<Party>): DebtSplit {
        return BalancedDebtSplit(payment)
    }
}
