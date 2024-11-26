package pocket.pay.tp3_hci.states

import pocket.pay.tp3_hci.model.Balance
import pocket.pay.tp3_hci.model.Card
import pocket.pay.tp3_hci.model.Payment
import pocket.pay.tp3_hci.model.User

data class AccountUiState(
    val isLoggedIn: Boolean = false,
    val currentUser: User? = null,
    val isFetching: Boolean = false,
    val error: Error? = null,
    val currentCard: Card? = null,
    val currentPayment: Payment? = null,
    val currentBalance: Balance? = Balance(0.0),

    val payments: List<Payment>? = emptyList(),
    val cards: List<Card>? = emptyList(),
)
