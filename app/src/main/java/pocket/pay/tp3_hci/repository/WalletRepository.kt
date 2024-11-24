package pocket.pay.tp3_hci.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import pocket.pay.tp3_hci.model.Card
import pocket.pay.tp3_hci.network.WalletRemoteDataSource

class WalletRepository (
    private val remoteDataSource: WalletRemoteDataSource
) {
    private val cardsMutex = Mutex()
    private var cards: List<Card> = emptyList()

    suspend fun getCards(refresh: Boolean = false): List<Card> {
        if (refresh || cards.isEmpty()) {
            val result = remoteDataSource.getCards()
            cardsMutex.withLock {
                this.cards = result.map { it.asModel() }
            }
        }
        return cardsMutex.withLock { this.cards }
    }

    suspend fun addCard(card: Card) : Card {
        val newCard = remoteDataSource.addCard(card.asNetworkModel()).asModel()
        cardsMutex.withLock{ this.cards = emptyList() }
        return newCard
    }

    suspend fun deleteCard(cardId: Int) {
        remoteDataSource.deleteCard(cardId)
        cardsMutex.withLock {
            this.cards = emptyList()
        }
    }
}