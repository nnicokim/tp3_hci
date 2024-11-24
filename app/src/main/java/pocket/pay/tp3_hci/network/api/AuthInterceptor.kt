package pocket.pay.tp3_hci.network.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import pocket.pay.tp3_hci.SessionManager


class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.loadAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}