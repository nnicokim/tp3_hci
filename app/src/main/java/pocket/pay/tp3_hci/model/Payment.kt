package pocket.pay.tp3_hci.model

import pocket.pay.tp3_hci.network.model.NetworkCard
import pocket.pay.tp3_hci.network.model.NetworkPayment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Payment (
    var id: Int? = null,
    var type: String,
    var amount: Float,
    var balanceBefore: Float,
    var balanceAfter: Float,
    var pending: Boolean,
    var linkUuid: String? = null,
    var createdAt: Date? = null,
    var updatedAt: Date? = null,
    var card: Card? = null
) {
    fun asNetworkModel(): NetworkPayment {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))

        return NetworkPayment(
            id = id,
            type = type,
            amount = amount,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt.let { dateFormat.format(createdAt!!) },
            updatedAt = updatedAt.let { dateFormat.format(updatedAt!!) },
            card = card?.asNetworkModel(),
        )
    }
}