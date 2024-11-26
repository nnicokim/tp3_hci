package pocket.pay.tp3_hci.network

import pocket.pay.tp3_hci.network.api.WalletApiService
import pocket.pay.tp3_hci.network.model.NetworkBalance
import pocket.pay.tp3_hci.network.model.NetworkCard

class WalletRemoteDataSource(
    private val walletApiService: WalletApiService
) : RemoteDataSource() {

    suspend fun getBalance(): NetworkBalance {
        return handleApiResponse {
            walletApiService.getBalance()
        }
    }

    suspend fun recharge(amount: Double): NetworkBalance {
        return handleApiResponse {
            walletApiService.recharge(amount)
        }
    }

    suspend fun getCards(): List<NetworkCard> {
        return handleApiResponse {
            walletApiService.getCards()
        }
    }

    suspend fun addCard(card: NetworkCard): NetworkCard {
        return handleApiResponse {
            walletApiService.addCard(card)
        }
    }

    suspend fun deleteCard(cardId: Int) {
        handleApiResponse {
            walletApiService.deleteCard(cardId)
        }
    }
}