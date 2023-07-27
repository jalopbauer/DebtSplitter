package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party

data class BalancedDebtSplit(val dept: Payment, val partyDebt : Map<Party, Double>) : DebtSplit {
    override fun amountOwned(): Double =
        dept.amount

    override fun ownedParty(): Party =
        dept.ownedParty

    override fun partyDebt(): Map<Party, Double> =
        partyDebt

}
