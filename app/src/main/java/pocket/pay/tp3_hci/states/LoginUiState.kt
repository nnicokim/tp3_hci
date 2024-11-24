package pocket.pay.tp3_hci.states

import pocket.pay.tp3_hci.model.Card
import pocket.pay.tp3_hci.model.User

data class LoginUiState(
    val isLoggedIn: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val cards: List<Card>? = null,
    val error: Error? = null
)

val LoginUiState.canGetCurrentUser: Boolean get() = isLoggedIn
