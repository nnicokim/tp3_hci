package pocket.pay.tp3_hci.network.api

import pocket.pay.tp3_hci.network.model.NetworkCredentials
import pocket.pay.tp3_hci.network.model.NetworkRegister
import pocket.pay.tp3_hci.network.model.NetworkToken
import pocket.pay.tp3_hci.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("user/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("user")
    suspend fun register(@Body user: NetworkRegister): Response<NetworkUser>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>

    @GET("user")
    suspend fun getCurrentUser(): Response<NetworkUser>
}