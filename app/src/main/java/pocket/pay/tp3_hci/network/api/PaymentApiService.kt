package pocket.pay.tp3_hci.network.api

import pocket.pay.tp3_hci.network.model.NetworkPayment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PaymentApiService {
    @POST("payment")
    suspend fun payCard(@Body cardPayment: NetworkPayment): Response<NetworkPayment>

    @POST("payment") // Si se paga con balance, lo restamos
    suspend fun payBalance(@Body balancePayment: NetworkPayment): Response<NetworkPayment>

//    @GET("payment")
//    suspend fun getPayments(): Response<List<NetworkPayment>>
}