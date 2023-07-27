package debtsplitter.partyDebt

import debtsplitter.amount.MoneyAmount
import debtsplitter.party.Party

data class PartyDebt(val partyDebt: Map<Party, MoneyAmount>) {
    fun amount() : Double =
        partyDebt.values.sumOf { it.amount }
}