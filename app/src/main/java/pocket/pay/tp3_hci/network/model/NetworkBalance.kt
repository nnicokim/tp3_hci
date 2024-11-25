package pocket.pay.tp3_hci.network.model

import kotlinx.serialization.Serializable
import pocket.pay.tp3_hci.model.Balance
import pocket.pay.tp3_hci.model.Payment
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkBalance (
    var balance: Float
) {
    fun asModel(): Balance {
        return Balance(
            balance = balance
        )
    }
}
