package pocket.pay.tp3_hci.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import pocket.pay.tp3_hci.R

data class Card(
    val cardholderName: String,
    val cardNumber: String,
    val expiryDate: String,
    val cvv: Int,
    val backgroundColor: Long // Color en hexa
)

class CardsViewModel : ViewModel() {

    private val _cards = mutableStateListOf<Card>()
    val cards: List<Card> = _cards

    private val _cardholderName = MutableStateFlow("")
    val cardholderName: StateFlow<String> = _cardholderName

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber

    private val _expiryDate = MutableStateFlow("")
    val expiryDate: StateFlow<String> = _expiryDate

    private val _cvv = MutableStateFlow("")
    val cvv: StateFlow<String> = _cvv

    private val _errorTextCardNumber = MutableStateFlow("")
    val errorTextCardNumber = _errorTextCardNumber.asStateFlow()

    private val _errorTextCardName = MutableStateFlow("")
    val errorTextCardName = _errorTextCardName.asStateFlow()

    private val _errorTextCardExpDate = MutableStateFlow("")
    val errorTextCardExpDate = _errorTextCardExpDate.asStateFlow()

    private val _errorTextCardCVV = MutableStateFlow("")
    val errorTextCardCVV = _errorTextCardCVV.asStateFlow()

    init {
        // Carga tarjetas iniciales. TODO: ver como es esto con la API
        _cards.addAll(
            listOf(
                Card(
                    cardholderName = "Hwa Pyoung Kim",
                    cardNumber = "**** **** **** 4952",
                    expiryDate = "06/25",
                    cvv = 123,
                    backgroundColor = 0XFFFEE12B
                ),
                Card(
                    cardholderName = "Nicolas Kim",
                    cardNumber = "**** **** **** 5915",
                    expiryDate = "09/25",
                    cvv = 254,
                    backgroundColor = 0XFF000000
                )
            )
        )
    }

    private fun isValidNumber(number: String): Boolean{
        val regex = Regex("^\\d{16}$")
        return regex.matches(number)
    }

    fun validateCardNumber(onValidNumber: () -> Unit){
        if(_cardNumber.value.isBlank()){
            _errorTextCardNumber.value = "empty"
        } else if (!isValidNumber(_cardNumber.value)){
            _errorTextCardNumber.value = "invalid"
        } else {
            onValidNumber()
        }
    }

    fun updateCardNumber(newNumber: String) {
        _cardNumber.value = newNumber
    }

    fun validateCardName(onValidName: () -> Unit){
        if(_cardholderName.value.isBlank()){
            _errorTextCardName.value = "empty"
        } else {
            onValidName()
        }
    }

    fun updateCardName(newName: String) {
        _cardholderName.value = newName
    }

    private fun isValidExpDate(expdate: String): Boolean{
        val regex = Regex("^(0[1-9]|1[0-2])/\\d{2}$")
        return regex.matches(expdate)
    }

    fun validateCardExpDate(onValidExpDate: () -> Unit){
        if(_expiryDate.value.isBlank()){
            _errorTextCardExpDate.value = "empty"
        } else if (!isValidExpDate(_expiryDate.value)){
            _errorTextCardExpDate.value = "invalid"
        } else {
            onValidExpDate()
        }
    }

    fun updateCardExpDate(newExpDate: String) {
        _expiryDate.value = newExpDate
    }

    private fun isValidCVV(cvv: String): Boolean{
        val regex = Regex("^\\d{3}$")
        return regex.matches(cvv)
    }

    fun validateCardCVV(onValidCVV: () -> Unit){
        if(_cvv.value.isBlank()){
            _errorTextCardCVV.value = "empty"
        } else if (!isValidCVV(_cvv.value)){
            _errorTextCardCVV.value = "invalid"
        } else {
            onValidCVV()
        }
    }

    fun updateCardCvv(newCVV: String) {
        _cvv.value = newCVV
    }

    fun addCard() {
        _cards.add(
            Card(
                cardholderName = _cardholderName.value,
                cardNumber = _cardNumber.value,
                expiryDate = _expiryDate.value,
                cvv = _cvv.value.toInt(),
                backgroundColor = 0xFFF28418
            )
        )
    }

    fun removeCard(card: Card) {
        _cards.remove(card)
    }
}
