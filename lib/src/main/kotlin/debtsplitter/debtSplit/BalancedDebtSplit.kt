package debtsplitter.debtSplit

import debtsplitter.amount.MoneyAmount
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt

data class BalancedDebtSplit(val ownedParty: Party, val partyDebt : PartyDebt) : DebtSplit {
    override fun amountOwned(): MoneyAmount =
        partyDebt.amount()

    override fun ownedParty(): Party =
        ownedParty

    override fun partyDebt(): PartyDebt =
        partyDebt

}
