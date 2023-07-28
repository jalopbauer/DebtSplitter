package debtsplitter.partyDebt

import debtsplitter.party.Party
import util.money.Money

data class PartyDebt(val partyDebt: Map<Party, Money>) {
    fun amount() : Money =
        partyDebt.values.reduce { acc, money ->  acc.plus(money) }
}