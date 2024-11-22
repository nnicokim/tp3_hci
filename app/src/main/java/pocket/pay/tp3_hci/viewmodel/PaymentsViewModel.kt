package pocket.pay.tp3_hci.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class Payment(
    val companyName: String,
    val paymentAmount: Long,
    val paymentSource: String,
    val date: String
)

class PaymentsViewModel : ViewModel() {

    private val _payments = mutableStateListOf<Payment>()
    val payments: List<Payment> = _payments

    private val _companyName = MutableStateFlow("")
    val companyName: StateFlow<String> = _companyName

    private val _paymentAmount = MutableStateFlow(0f)
    val paymentAmount: StateFlow<Float> = _paymentAmount

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

    //Validation Functions
    fun validateCompanyName(onValidCompanyName: () -> Unit){
        if(_companyName.value.isBlank()){
            _errorTextCompanyName.value = "empty"
        } else {
            onValidCompanyName()
        }
    }

    private fun isValidPaymentAmount(amount: Float){

    }

    fun validatePaymentAmount(onValidPaymentAmount: () -> Unit){
        if(!isValidPaymentAmount(_paymentAmount.value)){
            _errorTextPaymentAmount.value = "invalid"
        } else {
            onValidPaymentAmount()
        }
    }

    private fun isValidPaymentSource(source: String){

    }

    fun validatePaymentSource(onValidExpDate: () -> Unit){
        if(_paymentSource.value.isBlank()){
            _errorTextPaymentSource.value = "empty"
        } else if (!isValidPaymentSource(_paymentSource.value)){
            _errorTextPaymentSource.value = "invalid"
        } else {
            onValidExpDate()
        }
    }

    //Update Functions

    fun updateCompanyName(newCompanyName: String) {
        _companyName.value = newCompanyName
    }
    fun updatePaymentAmount(newPaymentAmount: Float) {
        _paymentAmount.value = newPaymentAmount
    }
    fun updatePaymentSource(newPaymentSource: String) {
        _paymentSource.value = newPaymentSource
    }
}