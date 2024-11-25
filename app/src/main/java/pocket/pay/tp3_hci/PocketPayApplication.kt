package pocket.pay.tp3_hci

import android.app.Application
import pocket.pay.tp3_hci.network.PaymentRemoteDataSource
import pocket.pay.tp3_hci.network.UserRemoteDataSource
import pocket.pay.tp3_hci.network.WalletRemoteDataSource
import pocket.pay.tp3_hci.network.api.RetrofitClient
import pocket.pay.tp3_hci.repository.PaymentRepository
import pocket.pay.tp3_hci.repository.UserRepository
import pocket.pay.tp3_hci.repository.WalletRepository

class PocketPayApplication : Application() {
    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getUserApiService(this))

    private val walletRemoteDataSource: WalletRemoteDataSource
        get() = WalletRemoteDataSource(RetrofitClient.getWalletApiService(this))

    private val paymentRemoteDataSource: PaymentRemoteDataSource
        get() = PaymentRemoteDataSource(RetrofitClient.getPaymentApiService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val walletRepository: WalletRepository
        get() = WalletRepository(walletRemoteDataSource)

    val paymentRepository: PaymentRepository
        get() = PaymentRepository(paymentRemoteDataSource)

}