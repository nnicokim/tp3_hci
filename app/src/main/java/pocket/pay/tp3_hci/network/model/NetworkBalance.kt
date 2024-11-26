package pocket.pay.tp3_hci.network.model

import kotlinx.serialization.Serializable
import pocket.pay.tp3_hci.model.Balance

@Serializable
class NetworkBalance (
    var balance: Double
) {
    fun asModel(): Balance {
        return Balance(
            balance = balance
        )
    }
}
