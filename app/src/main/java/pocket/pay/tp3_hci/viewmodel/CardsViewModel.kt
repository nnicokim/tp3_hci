package pocket.pay.tp3_hci.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Card(
    val cardholderName: String,
    val cardNumber: String,
    val expiryDate: String,
    val backgroundColor: Long // Color en hexa
)

class CardsViewModel : ViewModel() {

    private val _cards = mutableStateListOf<Card>()
    val cards: List<Card> = _cards

    init {
        // Carga tarjetas iniciales. TODO: ver como es esto con la API
        _cards.addAll(
            listOf(
                Card(
                    cardholderName = "Hwa Pyoung Kim",
                    cardNumber = "**** **** **** 4952",
                    expiryDate = "06/25",
                    backgroundColor = 0XFFFEE12B
                ),
                Card(
                    cardholderName = "Nicolas Kim",
                    cardNumber = "**** **** **** 5915",
                    expiryDate = "09/25",
                    backgroundColor = 0XFF000000
                )
            )
        )
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun removeCard(card: Card) {
        _cards.remove(card)
    }
}
