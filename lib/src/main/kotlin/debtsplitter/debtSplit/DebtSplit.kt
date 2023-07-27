package debtsplitter.debtSplit

import debtsplitter.party.Party

sealed interface DebtSplit {
    fun amountOwned(): Double
    fun ownedParty(): Party
}