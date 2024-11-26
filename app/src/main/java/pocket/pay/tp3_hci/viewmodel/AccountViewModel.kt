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
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


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
        onErrorEmail : (String) -> Unit,
        onErrorPassword : (String) -> Unit,
        goToHome : () -> Unit
    ) {
        var flag = true
        if (email.isBlank()) {
            flag = false
            onErrorEmail("empty_email")
        } else if (!isEmailValid(email)) {
            onErrorPassword("invalid_email")
            flag = false
        }
        if (password.isBlank()) {
            onErrorPassword("empty_password")
            flag = false
        }
        if(flag){
            //logica para buscar la cuenta
            login(email, password)
            if(uiState.isLoggedIn){
                goToHome()
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

    fun logoutExit(goToLogin : () -> Unit) {
        logout {
            goToLogin()
        }
    }

    fun logout (onLogoutComplete: () -> Unit = {}) = runOnViewModelScope(
        block = {
            userRepository.logout()
            onLogoutComplete() // Ejecuta solo después de completar el logout
        },
        { state, _ -> state.copy(
            isLoggedIn = false,
            currentUser = null,
            cards = null,
            payments = null,
            currentCard = null,
            currentPayment = null,
            currentBalance = null,
            error = null,
            isFetching = false,
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

    //Add validations
    private fun isValidNumber(number: String): Boolean {
        return number.matches(Regex("\\d{16}"))
    }

    private fun isValidDate(date: String): Boolean {
        val regex = Regex("^((0[1-9])|(1[0-2]))/([0-9]{2})$")
        return date.matches(regex)
    }


    private fun isValidCVV(cvv: String): Boolean {
        return cvv.matches(Regex("\\d{3,4}"))
    }

    fun validateAndAddCard(
        number: String,
        expirationDate: String,
        fullName: String,
        cvv: String?,
        type: CardType,
        onErrorNumber : (String) -> Unit,
        onErrorName : (String) -> Unit,
        onErrorExpirationDate : (String) -> Unit,
        onErrorCVV : (String) -> Unit,
        goBackToCards : () -> Unit
    ) {
        var flag = true
        if (number.isBlank()) {
            onErrorNumber("empty_number") //arrojar error y agregar validacion de parametros
            flag = false
        } else if(!isValidNumber(number)){ //16 digitos
            onErrorNumber("invalid_number")
            flag = false
        } else {
            onErrorNumber("Valid")
        }
        if (expirationDate.isBlank()) {
            onErrorExpirationDate("empty_date")
            flag = false
        } else if(!isValidDate(expirationDate)){ //mm/yy
            onErrorExpirationDate("invalid_number")
            flag = false
        }  else {
            onErrorExpirationDate("Valid")
        }
        if (fullName.isBlank()) {
            onErrorName("empty_name")
            flag = false
        } else {
            onErrorName("Valid")
        }
        if (cvv.isNullOrBlank()) {
            onErrorCVV("empty_cvv")
            flag = false
        }  else if(!isValidCVV(cvv)){ //3 digitos
            onErrorCVV("invalid_number")
            flag = false
        } else {
            onErrorCVV("Valid")
        }
        if (flag){
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
        { state, response -> state.copy(currentBalance = response) }
    )

    fun recharge(amount: Double) = runOnViewModelScope(
        {
            walletRepository.recharge(amount)
        },
        { state, response -> state.copy(currentBalance = response)
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

    fun addPayment(payment: Payment) = runOnViewModelScope(
        {
//            val payment = Payment(
//                id = 1,
//                description = "Nike",
//                amount = 1000f,
//                type = PaymentType.BALANCE,
//                pending = false,
//                receiverEmail = "dashawn45@ethereal.email")
            paymentRepository.addPayment(payment)
        },
        { state, response ->
            state.copy(
                currentPayment = response,
                payments = null
            )
        }
    )

    fun validateAndAddPayment(
        id: Int?,
        description: String,
        amount: Float,
        receiverEmail: String,
        onErrorId : (String) -> Unit,
        onErrorDescription : (String) -> Unit,
        onErrorAmount : (String) -> Unit,
        onErrorReceiverEmail : (String) -> Unit,
        goBackToPayment : () -> Unit
    ) {
        var flag = true
        if(!isValidId(id)){ //Int
            onErrorId("invalid_id")
            flag = false
        } else {
            onErrorId("Valid")
        }
        if (description.isBlank()) {
            onErrorDescription("empty_description")
            flag = false
        } else {
            onErrorDescription("Valid")
        }
        if (!isValidAmount(amount)) {
            onErrorAmount("invalid_amount")
            flag = false
        } else {
            onErrorAmount("Valid")
        }
        if (receiverEmail.isBlank()) {
            onErrorReceiverEmail("empty_email")
            flag = false
        }  else if(!isEmailValid(receiverEmail)){
            onErrorReceiverEmail("invalid_email")
            flag = false
        } else {
            onErrorReceiverEmail("Valid")
        }
        if (flag){
            val payment = Payment(
                id = id,
                description = description,
                amount = amount,
                type = PaymentType.BALANCE,
                pending = false,
                receiverEmail = receiverEmail)
            addPayment(payment)
            goBackToPayment()
        }
    }

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
            Log.e(TAG, "Coroutine execution failed (runOnViewModelScope)", e)
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