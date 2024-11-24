package pocket.pay.tp3_hci.network.model

import pocket.pay.tp3_hci.model.Payment
import java.text.SimpleDateFormat
import java.util.Locale

class NetworkPayment (
    var id: Int,
    var type: String, // Balance o Card
    var amount: Float,
    var balanceBefore: Float,
    var balanceAfter: Float,
    var pending: Boolean,
    var linkUuid: String? = null,
    var createdAt: String,
    var updatedAt: String,
    var card: NetworkCard?
) {
    fun asModel(): Payment {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return Payment(
            id = id,
            type = type,
            amount = amount,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = dateFormat.parse(createdAt),
            updatedAt = dateFormat.parse(updatedAt),
            card = card?.asModel()
        )
    }
}