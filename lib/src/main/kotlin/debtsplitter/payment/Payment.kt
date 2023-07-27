package debtsplitter.payment

import debtsplitter.amount.MoneyAmount
import debtsplitter.party.Party

data class Payment(val ownedParty: Party, val amount: MoneyAmount)
