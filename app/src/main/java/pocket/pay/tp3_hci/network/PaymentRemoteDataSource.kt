package pocket.pay.tp3_hci.network

import pocket.pay.tp3_hci.network.api.PaymentApiService

class PaymentRemoteDataSource (
    private val paymentApiService: PaymentApiService
) : RemoteDataSource() {
}