package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt

data class BalancedDebtSplit(val dept: Payment, val partyDebt : PartyDebt) : DebtSplit {
    override fun amountOwned(): Double =
        dept.amount

    override fun ownedParty(): Party =
        dept.ownedParty

    override fun partyDebt(): PartyDebt =
        partyDebt

}
