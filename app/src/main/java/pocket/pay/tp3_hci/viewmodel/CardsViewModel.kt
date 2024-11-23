package pocket.pay.tp3_hci.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import pocket.pay.tp3_hci.R

data class Card (
    val cardholderName: String,
    val cardNumber: String,
    val expiryDate: String,
    val cvv: Int,
    val backgroundColor: Long // Color en hexa
)

class CardsViewModel : ViewModel() {

    private val _cards = MutableStateFlow(listOf<Card>())//MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> = _cards


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

    private fun isValidNumber(number: String): Boolean{
        val regex = Regex("^\\d{16}$")
        return regex.matches(number)
    }

    private fun validateCardNumber() : Boolean{
        if(_cardNumber.value.isBlank()){
            _errorTextCardNumber.value = "empty"
            return false
        } else if (!isValidNumber(_cardNumber.value)){
            _errorTextCardNumber.value = "invalid"
            return false
        }
        _errorTextCardNumber.value = ""
        return true
    }

    fun updateCardNumber(newNumber: String) {
        _cardNumber.value = newNumber
    }

    fun validateCardName() : Boolean{
        if(_cardholderName.value.isBlank()){
            _errorTextCardName.value = "empty"
            return false
        }
        _errorTextCardName.value = ""
        return true
    }

    fun updateCardName(newName: String) {
        _cardholderName.value = newName
    }

    private fun isValidExpDate(expdate: String): Boolean{
        val regex = Regex("^(0[1-9]|1[0-2])/\\d{2}$")
        return regex.matches(expdate)
    }

    private fun validateCardExpDate() : Boolean{
        if(_expiryDate.value.isBlank()){
            _errorTextCardExpDate.value = "empty"
            return false
        } else if (!isValidExpDate(_expiryDate.value)){
            _errorTextCardExpDate.value = "invalid"
            return false
        } else {
            _errorTextCardExpDate.value = ""
            return true
        }
    }

    fun updateCardExpDate(newExpDate: String) {
        _expiryDate.value = newExpDate
    }

    private fun isValidCVV(cvv: String): Boolean{
        val regex = Regex("^\\d{3}$")
        return regex.matches(cvv)
    }

    private fun validateCardCVV() : Boolean{
        if(_cvv.value.isBlank()){
            _errorTextCardCVV.value = "empty"
            return false
        } else if (!isValidCVV(_cvv.value)){
            _errorTextCardCVV.value = "invalid"
            return false
        }
        _errorTextCardCVV.value = ""
        return true
    }

    fun updateCardCvv(newCVV: String) {
        _cvv.value = newCVV
    }

    fun isCardDataValid(onValidCard: () -> Unit) {
        val numFlag = validateCardNumber()
        val nameFlag = validateCardName()
        val dateFlag = validateCardExpDate()
        val cvv = validateCardCVV()
        if (numFlag && nameFlag && dateFlag && cvv){
            addCard()
            onValidCard()
        }
    }

    private fun addCard() {
            val newCard = Card(
                cardholderName = _cardholderName.value,
                cardNumber = _cardNumber.value,
                expiryDate = _expiryDate.value,
                cvv = _cvv.value.toInt(),
                backgroundColor = 0xFFF28418
            )
            _cards.value = _cards.value + newCard
        Log.d("CardsViewModel", "New card added: $newCard. Total cards: ${_cards.value.size}")
         //   resetCardData()
    }

    private fun resetCardData() {
        _cardholderName.value = ""
        _cardNumber.value = ""
        _expiryDate.value = ""
        _cvv.value = ""
    }


    fun removeCard(card: Card) {
        _cards.value = _cards.value.filter { it != card }
        Log.d("CardsViewModel", "Card removed: $card. Remaining cards: ${_cards.value}")
    }

}

