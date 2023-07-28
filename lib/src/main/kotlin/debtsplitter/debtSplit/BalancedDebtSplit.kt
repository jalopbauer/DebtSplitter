package debtsplitter.debtSplit

import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt
import util.money.Money

data class BalancedDebtSplit(val ownedParty: Party, val partyDebt : PartyDebt) : DebtSplit {
    override fun amountOwned(): Money =
        partyDebt.amount()

    override fun ownedParty(): Party =
        ownedParty

    override fun partyDebt(): PartyDebt =
        partyDebt

}
