package debtsplitter.partyDebt

import debtsplitter.amount.MoneyAmount
import debtsplitter.party.Party

data class PartyDebt(val partyDebt: Map<Party, MoneyAmount>) {
    fun amount() : MoneyAmount =
        MoneyAmount(partyDebt.values.sumOf { it.amount })
}