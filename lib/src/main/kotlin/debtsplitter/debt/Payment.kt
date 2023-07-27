package debtsplitter.debt

import debtsplitter.party.Party

data class Payment(val ownedParty: Party, val amount: Double)
