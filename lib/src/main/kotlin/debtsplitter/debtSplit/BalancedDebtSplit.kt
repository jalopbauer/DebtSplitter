package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party

data class BalancedDebtSplit(val dept: Payment) : DebtSplit {
    override fun amountOwned(): Double =
        dept.amount

    override fun ownedParty(): Party =
        dept.ownedParty

}
