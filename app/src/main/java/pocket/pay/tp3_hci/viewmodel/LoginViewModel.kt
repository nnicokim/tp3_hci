package pocket.pay.tp3_hci.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pocket.pay.tp3_hci.DataSourceException
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.SessionManager
import pocket.pay.tp3_hci.repository.UserRepository
import pocket.pay.tp3_hci.repository.WalletRepository
import pocket.pay.tp3_hci.states.LoginUiState

class LoginViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState( isLoggedIn = sessionManager.loadAuthToken() != null))
            private set

    fun login(username: String, password: String) = runOnViewModelScope (
    { userRepository.login(username, password) },
    { state, _ -> state.copy(isLoggedIn = true) }
    )

    fun logout () = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ -> state.copy(
            isLoggedIn = false,
            currentUser = null,
            cards = null
            )
        }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun getCards() = runOnViewModelScope(
        { walletRepository.getCards() },
        { state, response -> state.copy(cards = response) }
    )


    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (LoginUiState, R) -> LoginUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
            Log.e(TAG, "Coroutine execution failed", e)
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error("Code: ${e.code}, Message: ${e.message}")
        } else {
            Error("An unexpected error occurred")
        }
    }

    companion object {
        private const val TAG = "LoginViewModel"

        fun provideFactory(
            application: PocketPayApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(
                    application.sessionManager,
                    application.userRepository,
                    application.walletRepository
                ) as T
            }
        }
    }
}


//    private val _email = MutableStateFlow("")
//    val email: StateFlow<String> = _email
//
//    private val _password = MutableStateFlow("")
//    val password: StateFlow<String> = _password
//
//    private val _errorMessage = MutableStateFlow("")
//    val errorMessage: StateFlow<String> = _errorMessage
//
//    fun updateEmail(newEmail: String) {
//        _email.value = newEmail
//    }
//
//    fun updatePassword(newPassword: String) {
//        _password.value = newPassword
//    }
//
//    fun validateAndLogin(
//        onLoginSuccess: () -> Unit
//    ) {
//        if (_email.value.isBlank()) {
//            _errorMessage.value = "Email cannot be empty"
//        } else if (_password.value.isBlank()) {
//            _errorMessage.value = "Password cannot be empty"
//        } else if (!isEmailValid(_email.value)) {
//            _errorMessage.value = "Invalid email format"
//        } else {
//            // Lógica de login
//            onLoginSuccess()
//        }
//    }
//
//    private fun isEmailValid(email: String): Boolean {
//        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
//    }



