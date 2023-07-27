package debtsplitter.debtSplit

import debtsplitter.payment.Payment
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt

data class BalancedDebtSplit(val ownedParty: Party, val partyDebt : PartyDebt) : DebtSplit {
    override fun amountOwned(): Double =
        partyDebt.amount()

    override fun ownedParty(): Party =
        ownedParty

    override fun partyDebt(): PartyDebt =
        partyDebt

}
