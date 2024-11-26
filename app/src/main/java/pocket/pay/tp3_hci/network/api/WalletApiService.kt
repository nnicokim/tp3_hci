package pocket.pay.tp3_hci.network.api

import pocket.pay.tp3_hci.network.model.NetworkBalance
import pocket.pay.tp3_hci.network.model.NetworkCard
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WalletApiService {

    @GET("wallet/balance")
    suspend fun getBalance(): Response<NetworkBalance>

    @POST("wallet/recharge")
    suspend fun recharge(@Body amount: Double): Response<NetworkBalance>

    @GET("wallet/cards")
    suspend fun getCards(): Response<List<NetworkCard>>

    @POST("wallet/cards")
    suspend fun addCard(@Body card: NetworkCard): Response<NetworkCard>

    @DELETE("wallet/cards/{cardId}")
    suspend fun deleteCard(@Path("cardId") cardId: Int): Response<Unit>
}