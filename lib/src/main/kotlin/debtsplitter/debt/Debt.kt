package debtsplitter.debt

import debtsplitter.party.Party

data class Debt(val ownedParty: Party, val amount: Double)
