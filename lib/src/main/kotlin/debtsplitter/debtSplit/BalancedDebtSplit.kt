package debtsplitter.debtSplit

import debtsplitter.debt.Debt
import debtsplitter.party.Party

data class BalancedDebtSplit(val dept: Debt) : DebtSplit {
    override fun amountOwned(): Double =
        dept.amount

    override fun ownedParty(): Party =
        dept.ownedParty

}
