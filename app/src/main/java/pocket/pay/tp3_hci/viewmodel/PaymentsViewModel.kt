package pocket.pay.tp3_hci.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class Payment(
    val companyName: String,
    val paymentAmount: String,
    val paymentSource: String,
    val date: String
)

class PaymentsViewModel : ViewModel() {

    private val _payments = mutableStateListOf<Payment>()
    val payments: List<Payment> = _payments

    private val _companyName = MutableStateFlow("")
    val companyName: StateFlow<String> = _companyName

    private val _paymentAmount = MutableStateFlow("")
    val paymentAmount: StateFlow<String> = _paymentAmount

    private val _paymentSource = MutableStateFlow("")
    val paymentSource: StateFlow<String> = _paymentSource

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> = _date

    private val _errorTextCompanyName = MutableStateFlow("")
    val errorTextCompanyName = _errorTextCompanyName.asStateFlow()

    private val _errorTextPaymentAmount = MutableStateFlow("")
    val errorTextPaymentAmount = _errorTextPaymentAmount.asStateFlow()

    private val _errorTextPaymentSource = MutableStateFlow("")
    val errorTextPaymentSource = _errorTextPaymentSource.asStateFlow()

    private val _errorTextDate = MutableStateFlow("")
    val errorTextDate = _errorTextDate.asStateFlow()

    private fun isValidPaymentSource(source: String): Boolean{
        return true
    }

    fun validatePayment(
        homeViewModel: HomeViewModel,
        onValidPayment: () -> Unit
    ) {
        var isValid = true

        if (_companyName.value.isBlank()) {
            _errorTextCompanyName.value = "empty"
            isValid = false
        } else {
            _errorTextCompanyName.value = ""
        }

        val amount = _paymentAmount.value.toFloatOrNull()
        if (amount == null || amount <= 0 || homeViewModel.balance.value < amount) {
            _errorTextPaymentAmount.value = "invalid"
            isValid = false
        } else {
            _errorTextPaymentAmount.value = ""
        }


        if (_paymentSource.value.isBlank()) {
            _errorTextPaymentSource.value = "empty"
            isValid = false
        } else if (!isValidPaymentSource(_paymentSource.value)) {
            _errorTextPaymentSource.value = "invalid"
            isValid = false
        } else {
            _errorTextPaymentSource.value = ""
        }

        if (isValid) {
            addPayment()
            onValidPayment()
        }
    }

    //Update Functions

    fun updateCompanyName(newCompanyName: String) {
        _companyName.value = newCompanyName
    }
    fun updatePaymentAmount(newPaymentAmount: String) {
        _paymentAmount.value = newPaymentAmount
    }
    fun updatePaymentSource(newPaymentSource: String) {
        _paymentSource.value = newPaymentSource
    }


    @SuppressLint("SuspiciousIndentation")
    private fun addPayment(){
        val newPayment = Payment(
            companyName = _companyName.value,
            paymentAmount = _paymentAmount.value,
            paymentSource = _paymentSource.value,
            date = _date.value
        )
            _payments.add(newPayment)
    }
}