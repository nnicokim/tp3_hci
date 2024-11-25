package pocket.pay.tp3_hci.states

import pocket.pay.tp3_hci.model.Card
import pocket.pay.tp3_hci.model.Payment
import pocket.pay.tp3_hci.model.User

data class AccountUiState(
    val isLoggedIn: Boolean = false,
    val currentUser: User? = null,
    val isFetching: Boolean = false,
    val error: Error? = null,

    val payments: List<Payment>? = emptyList(),
    val cards: List<Card>? = emptyList(),
)
