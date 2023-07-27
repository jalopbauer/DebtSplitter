package debtsplitter.debtSplit

import debtsplitter.debt.Debt
import debtsplitter.party.Party

data object DebtSplittingStrategy {
    fun splitEqually(debt: Debt, borrowedParties: List<Party>): DebtSplit {
        return BalancedDebtSplit(debt)
    }
}
