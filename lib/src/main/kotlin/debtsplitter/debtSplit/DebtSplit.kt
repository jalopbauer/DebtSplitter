package debtsplitter.debtSplit

import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt

sealed interface DebtSplit {
    fun amountOwned(): Double
    fun ownedParty(): Party
    fun partyDebt() : PartyDebt
}