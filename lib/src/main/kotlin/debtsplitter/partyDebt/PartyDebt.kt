package debtsplitter.partyDebt

import debtsplitter.party.Party

data class PartyDebt(val partyDebt : Map<Party, Double>) {
    fun amount() : Double =
        partyDebt.values.sum()
}