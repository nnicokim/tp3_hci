package pocket.pay.tp3_hci.model

import pocket.pay.tp3_hci.network.model.NetworkCard
import pocket.pay.tp3_hci.network.model.NetworkPayment
import java.text.SimpleDateFormat
import java.util.Locale

data class Payment (
    var id: Int? = null,
    var type: PaymentType? = PaymentType.UNKNOWN,
    var amount: Float = 0f,
    var description: String? = null,
    var pending: Boolean = false,
    var receiverEmail: String? = null,
    var linkUuid: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var card: Card? = null
) {
    fun asNetworkModel(): NetworkPayment {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))

        return NetworkPayment(
            id = id,
            type = when (type) {
                PaymentType.BALANCE -> "BALANCE"
                PaymentType.CREDIT -> "CARD"
                else -> "UNKNOWN"
            },
            amount = amount,
            description = description.orEmpty(),
            pending = pending,
            receiverEmail = receiverEmail.orEmpty(),
            linkUuid = linkUuid,
            createdAt = createdAt?.let { runCatching { dateFormat.parse(it) }.getOrNull() }.toString(),
            updatedAt = updatedAt?.let { runCatching { dateFormat.parse(it) }.getOrNull() }.toString(),
            card = card?.asNetworkModel()
        )
    }
}