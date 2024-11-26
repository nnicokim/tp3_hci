package pocket.pay.tp3_hci.viewmodel

import androidx.lifecycle.ViewModel
import pocket.pay.tp3_hci.SessionManager
import pocket.pay.tp3_hci.repository.UserRepository
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import pocket.pay.tp3_hci.DataSourceException
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.repository.WalletRepository
import androidx.lifecycle.ViewModelProvider
import pocket.pay.tp3_hci.model.Card
import pocket.pay.tp3_hci.model.CardType
import pocket.pay.tp3_hci.model.Payment
import pocket.pay.tp3_hci.model.PaymentType
import pocket.pay.tp3_hci.network.model.NetworkRegister
import pocket.pay.tp3_hci.repository.PaymentRepository
import pocket.pay.tp3_hci.states.AccountUiState


class AccountViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val walletRepository: WalletRepository,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    var uiState by mutableStateOf(AccountUiState(isLoggedIn = sessionManager.loadAuthToken() != null))
        private set

    fun login(username: String, password: String) = runOnViewModelScope (
        { userRepository.login(username, password) },
        { state, _ -> state.copy(isLoggedIn = true) }
    )

    fun verify(code: String) = runOnViewModelScope(
        { userRepository.verify(code)},
        { state, _ -> state.copy() }
    )

    fun validateAndLogin(
        email : String,
        password : String,
        onError : (String) -> Unit,
        goToHome : () -> Unit
    ) {
        if (email.isBlank()) {
            onError("Email cannot be empty")
        } else if (password.isBlank()) {
            onError("Password cannot be empty")
        } else if (!isEmailValid(email)) {
            onError("Invalid email format")
        } else {
            login(email, password)
                goToHome()
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
            userRepository.register(networkRegister)
        },
        { state, _ -> state.copy(isLoggedIn = true) }
    )

    fun validateAndRegister(
        firstname: String,
        lastname: String,
        birthdate: String,
        email: String,
        password: String,
        onError: (String) -> Unit,
        goToVerify : () -> Unit
    ) {
        if (firstname.isBlank()) {
            onError("First name cannot be empty")
        } else if (lastname.isBlank()) {
            onError("Last name cannot be empty")
        } else if (birthdate.isBlank()) {
            onError("birth date cannot be empty")
        } else if (email.isBlank()) {
            onError("Email cannot be empty")
        } else if (password.isBlank()) {
            onError("Password field cannot be empty")
        } else {
            register(firstname, lastname, email, birthdate, password)
            goToVerify()
        }
    }

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun logoutExit (goToLogin : () -> Unit) {
        logout()
        goToLogin()
    }

    fun logout () = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ -> state.copy(
            isLoggedIn = false,
            currentUser = null,
        )
        }
    )

    fun addCard(card: Card) = runOnViewModelScope(
        {
            walletRepository.addCard(card)
        },
        { state, response ->
            state.copy(
                currentCard = response,
                cards = null
            )
        }
    )

    fun validateAndAddCard(
        number: String,
        expirationDate: String,
        fullName: String,
        cvv: String?,
        type: CardType,
        onError : (String) -> Unit,
        goBackToCards : () -> Unit
    ) {
        if (number.isBlank()) {
            onError("Email cannot be empty") //arrojar error y agregar validacion de parametros
        } else if (expirationDate.isBlank()) {
            onError("Password cannot be empty")
        } else if (fullName.isBlank()) {
            onError("Password cannot be empty")
        } else if (cvv.isNullOrBlank()) {
            onError("Password cannot be empty")
        } else {
            val card = Card(number = number,
                fullName = fullName,
                expirationDate = expirationDate,
                cvv = cvv,
                type = type)
            addCard(card)
            goBackToCards()
        }
    }

    fun getBalance() = runOnViewModelScope(
        { walletRepository.getBalance() },
        { state, response -> state.copy(currentBalance = response.balance) }
    )

    fun recharge(amount: Double) = runOnViewModelScope(
        {
            walletRepository.recharge(amount)
        },
        { state, response -> state.copy(currentBalance = response.balance)
        }
    )

    fun getCards() = runOnViewModelScope(
        { walletRepository.getCards(true) },
        { state, response -> state.copy(cards = response) }
    )

    //null pointer exception

//    fun addPayment(type: PaymentType,
//                   amount: Float,
//                   description: String,
//                   pending: Boolean,
//                   receiverEmail: String
//                   ) = runOnViewModelScope(
//        {
//            val payment = Payment(
//                type = type,
//                amount = amount,
//                description = description,
//                pending = pending,
//                receiverEmail = receiverEmail
//            )
//            paymentRepository.addPayment(payment)
//        },
//        { state, _ ->
//            state.copy()
//        }
//    )

    fun addPayment(/*payment: Payment*/) = runOnViewModelScope(
        {
            val payment = Payment(
                id = 1,
                description = "Nike",
                amount = 1000f,
                type = PaymentType.BALANCE,
                pending = false,
                receiverEmail = "dashawn45@ethereal.email")
            paymentRepository.addPayment(payment)
        },
        { state, response ->
            state.copy(
                currentPayment = response,
                payments = null
            )
        }
    )

//    fun validateAndAddPayment(
//        type: PaymentType,
//        amount: Float?,
//        description: String,
//        pending: Boolean,
//        receiverEmail: String,
//        onError: (String) -> Unit,
//        goBackToPayment: () -> Unit
//    ) {
//        if (description.isBlank()) {
//            onError("Email cannot be empty") //arrojar error y agregar validacion de parametros
//        } else if (amount != null) {
//            if (amount <= 0) {
//                onError("Password cannot be empty")
//            } else {
//                addPayment(type, amount,description,pending, receiverEmail)
//                goBackToPayment()
//            }
//        }
//    }

//    fun payCard(number: String,
//                expirationDate: String,
//                fullName: String,
//                cvv: String?,
//                type: CardType,) = runOnViewModelScope(
//        {
//            val payment = Payment(
//                number = number,
//                expirationDate = expirationDate,
//                fullName = fullName,
//                cvv = cvv,
//                type = type,
//            )
//            paymentRepository.payCard(payment)
//        },
//        { state, response ->
//            state.copy(
//                currentCard = response,
//                cards = null
//            )
//        }
//    )



    //validar email, contraseña, pagos

    //payBalance, payCards, addcards son para hacer validate
    //fijarse como funciona getCards
    //



    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (AccountUiState, R) -> AccountUiState
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
        private const val TAG = "AccountViewModel"
        fun provideFactory(
            application: PocketPayApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AccountViewModel(
                    application.sessionManager,
                    application.userRepository,
                    application.walletRepository,
                    application.paymentRepository
                ) as T
            }
        }
    }
}