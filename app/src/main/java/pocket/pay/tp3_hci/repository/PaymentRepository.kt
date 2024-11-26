package pocket.pay.tp3_hci.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import pocket.pay.tp3_hci.model.Card
import pocket.pay.tp3_hci.model.Payment
import pocket.pay.tp3_hci.network.PaymentRemoteDataSource

class PaymentRepository (
    private val remoteDataSource: PaymentRemoteDataSource
) {
    private val paymentsMutex = Mutex()
    private var payments: List<Payment> = emptyList()

    suspend fun getPayments(refresh: Boolean = false): List<Payment> {
        if (refresh || payments.isEmpty()) {
            val result = remoteDataSource.getPayments()
            paymentsMutex.withLock {
                this.payments = result.map { it.asModel() }
            }
        }
        return paymentsMutex.withLock { this.payments }
    }

    suspend fun addPayment(payment: Payment) : Payment {
        val newPayment = remoteDataSource.addPayment(payment.asNetworkModel()).asModel()
        paymentsMutex.withLock{ this.payments = emptyList() }
        return newPayment
    }
}