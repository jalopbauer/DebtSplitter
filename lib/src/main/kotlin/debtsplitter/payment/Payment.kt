package debtsplitter.payment

import debtsplitter.party.Party
import util.money.Money

data class Payment(val ownedParty: Party, val amount: Money)
