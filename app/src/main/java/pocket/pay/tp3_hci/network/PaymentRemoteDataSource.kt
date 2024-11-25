package pocket.pay.tp3_hci.network

import pocket.pay.tp3_hci.network.api.PaymentApiService
import pocket.pay.tp3_hci.network.model.NetworkCard
import pocket.pay.tp3_hci.network.model.NetworkPayment

class PaymentRemoteDataSource (
    private val paymentApiService: PaymentApiService
) : RemoteDataSource() {

//    suspend fun getPayments(): List<NetworkPayment> {
//        return handleApiResponse {
//            paymentApiService.getPayments()
//        }
//    }

    suspend fun payCard(payment: NetworkPayment): NetworkPayment {
        return handleApiResponse {
            paymentApiService.payCard(payment)
        }
    }

    suspend fun payBalance(payment: NetworkPayment): NetworkPayment {
        return handleApiResponse {
            paymentApiService.payBalance(payment)
        }
    }
}