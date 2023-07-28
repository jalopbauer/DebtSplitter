package debtsplitter.debtSplit

import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt
import util.money.Money

sealed interface DebtSplit : DebtSplittingStrategyResponse {
    fun amountOwned(): Money
    fun ownedParty(): Party
    fun partyDebt() : PartyDebt
}