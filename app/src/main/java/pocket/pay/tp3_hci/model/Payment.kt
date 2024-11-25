package pocket.pay.tp3_hci.model

import pocket.pay.tp3_hci.network.model.NetworkCard
import pocket.pay.tp3_hci.network.model.NetworkPayment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Payment (
    var id: Int? = 0,
    var type: PaymentType, // Balance o Card
    var amount: Float,
    var description: String,
    var pending: Boolean,
    var linkUuid: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var card: Card? = null
) {
    fun asNetworkModel(): NetworkPayment {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))

        return NetworkPayment(
            id = id,
            type = type,
            amount = amount,
            description = description,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt.let { dateFormat.format(createdAt!!) },
            updatedAt = updatedAt.let { dateFormat.format(updatedAt!!) },
            card = card?.asNetworkModel(),
        )
    }
}