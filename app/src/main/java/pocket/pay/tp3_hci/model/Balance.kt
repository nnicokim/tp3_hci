package pocket.pay.tp3_hci.model

import pocket.pay.tp3_hci.network.model.NetworkBalance
import pocket.pay.tp3_hci.network.model.NetworkPayment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Balance(
    var balance: Float
) {
    fun asNetworkModel(): NetworkBalance {
        return NetworkBalance(
            balance = balance
        )
    }
}
