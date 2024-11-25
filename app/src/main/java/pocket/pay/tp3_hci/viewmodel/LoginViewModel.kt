package pocket.pay.tp3_hci.viewmodel

import android.util.Log
import android.util.Patterns
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
import pocket.pay.tp3_hci.network.model.NetworkCode
import pocket.pay.tp3_hci.network.model.NetworkRegister
import pocket.pay.tp3_hci.repository.UserRepository
import pocket.pay.tp3_hci.repository.WalletRepository
import pocket.pay.tp3_hci.states.LoginUiState
import kotlin.math.log

class LoginViewModel(
    val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState( isLoggedIn = sessionManager.loadAuthToken() != null))
            private set

    fun login(username: String, password: String) = runOnViewModelScope (
    { userRepository.login(username, password) },
    { state, _ -> state.copy(isLoggedIn = true) }
    )

    fun updateEmail(email: String) {
        uiState = uiState.copy( email = email )
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy( password = password )
    }

    fun enterFirstname(firstname: String) {
        uiState = uiState.copy( firstname = firstname )
    }

    fun enterLastname(lastname: String) {
        uiState = uiState.copy( lastname = lastname )
    }

    fun enterBirthdate(birthdate: String) {
        uiState = uiState.copy( birthdate = birthdate )
    }

//    fun validateAndLogin(goToHome : () -> Unit) {
//        when {
//            uiState.email.isBlank() -> {
//                uiState = uiState.copy(emailError = "Email cannot be empty")
//            }
//            uiState.email.isNotBlank() -> {
//                uiState = uiState.copy(emailError = null)
//            }
//            uiState.password.isBlank() -> {
//                uiState = uiState.copy(passwordError = "Password cannot be empty")
//            }
//            !isEmailValid(uiState.email) -> {
//                uiState = uiState.copy(emailError = "Invalid email format")
//            }
//            else -> {
//                uiState = uiState.copy(emailError = null)
//                uiState = uiState.copy(passwordError = null)
//                login(uiState.email, uiState.password)
//                goToHome()
//            }
//        }
//    }

    fun validateAndLogin(goToHome: () -> Unit) {
        viewModelScope.launch {
            var emailError: String? = null
            var passwordError: String? = null

            when {
                uiState.email.isBlank() -> emailError = "Email cannot be empty"
                !isEmailValid(uiState.email) -> emailError = "Invalid email format"
                uiState.password.isBlank() -> passwordError = "Password cannot be empty"
            }

            uiState = uiState.copy(
                emailError = emailError,
                passwordError = passwordError
            )

            if (emailError == null && passwordError == null) {
                try {
                    // Realizar login y guardar token
                    userRepository.login(uiState.email, uiState.password)

                    // Obtener el token desde SessionManager
                    val token = sessionManager.loadAuthToken()
                    if (token != null) {
                        // Verificar el token
                        val user = userRepository.verify(NetworkCode(code = token))

                        uiState = uiState.copy(
                            currentUser = user,
                            isLoggedIn = true,
                            emailError = null,
                            passwordError = null
                        )
                        goToHome()
                    } else {
                        uiState = uiState.copy(
                            emailError = "Login failed: Token not found",
                            passwordError = null
                        )
                        Log.e(TAG, "Token not found")
                    }
                } catch (e: Exception) {
                    // Manejo de errores
                    uiState = uiState.copy(
                        error = handleError(e),
                        isFetching = false
                    )
                    Log.e(TAG, "Login failed", e)
                }
            }
        }
    }



    private fun isEmailValid(email: String): Boolean {
       return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        birthDate: String,
        password: String
    ) = runOnViewModelScope(
        {
            val networkRegister = NetworkRegister(
                firstName = firstName,
                lastName = lastName,
                email = email,
                birthDate = birthDate,
                password = password
            )
            userRepository.register(networkRegister) // Pasamos el objeto NetworkRegister
        },
        { state, _ -> state.copy(isLoggedIn = true) }
    )

    fun validateAndRegister(goToHome : () -> Unit) {
        when {
            uiState.firstname.isBlank() -> {
                Error("First name cannot be empty")
            }
            uiState.lastname.isBlank() -> {
                Error("Last name cannot be empty")
            }
            uiState.birthdate.isBlank() -> {
                Error("birth date cannot be empty")
            }
            uiState.email.isBlank() -> {
                uiState = uiState.copy(emailError = "Email cannot be empty")
            }
            uiState.email.isNotBlank() -> {
                uiState = uiState.copy(emailError = null)
            }
            uiState.password.isBlank() -> {
                uiState = uiState.copy(passwordError = "Password cannot be empty")
            }
            uiState.password.isNotBlank() -> {
                uiState = uiState.copy(passwordError = null)
            }
            else -> {
                register(uiState.firstname, uiState.lastname, uiState.email, uiState.birthdate, uiState.password)
                goToHome()
            }
        }
    }


    fun logout () = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ -> state.copy(
            firstname = "",
            lastname = "",
            birthdate = "",
            email = "",
            password = "",
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




