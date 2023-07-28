package debtsplitter.debtSplit

import debtsplitter.amount.MoneyAmount
import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt

sealed interface DebtSplit : DebtSplittingStrategyResponse {
    fun amountOwned(): MoneyAmount
    fun ownedParty(): Party
    fun partyDebt() : PartyDebt
}