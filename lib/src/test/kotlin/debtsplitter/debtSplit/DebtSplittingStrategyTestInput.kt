package debtsplitter.debtSplit

import debtsplitter.party.Party
import debtsplitter.partyDebt.PartyDebt
import debtsplitter.payment.Payment

data class DebtSplittingStrategyTestInput(val testName : String,val payment: Payment, val partyDebt: PartyDebt, val parties : List<Party>) {

    constructor(testName : String, payment: Payment, partyDebt: PartyDebt) : this(testName, payment, partyDebt, partyDebt.partyDebt.keys.toList())
}
