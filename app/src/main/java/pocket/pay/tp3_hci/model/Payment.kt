package pocket.pay.tp3_hci.model

import pocket.pay.tp3_hci.network.model.NetworkCard
import pocket.pay.tp3_hci.network.model.NetworkPayment
import java.text.SimpleDateFormat
import java.util.Locale

data class Payment (
    var id: Int? = null,
    var type: String? = null,
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
            type = type,
            amount = amount,
            description = description.orEmpty(), // Manejo de nulos
            pending = pending,
            receiverEmail = receiverEmail.orEmpty(),
            linkUuid = linkUuid,
            createdAt = createdAt?.let { runCatching { dateFormat.parse(it) }.getOrNull() }.toString(),
            updatedAt = updatedAt?.let { runCatching { dateFormat.parse(it) }.getOrNull() }.toString(),
            card = card?.asNetworkModel()
        )
    }
}