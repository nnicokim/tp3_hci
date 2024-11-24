package pocket.pay.tp3_hci.network.api

import android.content.Context
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    @Volatile
    private var instance: Retrofit? = null

    // Genera una unica instacia de Retrofit
    private fun getInstance(context: Context): Retrofit =
        instance ?: synchronized(this) {
            instance ?: buildRetrofit(context).also { instance = it }
    }

    private fun buildRetrofit(context: Context): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    fun getUserApiService(context: Context): UserApiService =
        getInstance(context).create(UserApiService::class.java)

    fun getWalletApiService(context: Context): WalletApiService =
        getInstance(context).create(WalletApiService::class.java)

    fun getPaymentApiService(context: Context): PaymentApiService =
        getInstance(context).create(PaymentApiService::class.java)
}