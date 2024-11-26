package pocket.pay.tp3_hci.network.model

import pocket.pay.tp3_hci.model.CardType
import pocket.pay.tp3_hci.model.Payment
import pocket.pay.tp3_hci.model.PaymentType
import java.text.SimpleDateFormat
import java.util.Locale

class NetworkPayment (
    var id: Int?,
    var type: String, // Balance o Card
    var amount: Double,
    var description: String,
    var pending: Boolean,
    var linkUuid: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var card: NetworkCard? = null
) {
    fun asModel(): Payment {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return Payment(
            id = id,
            type = when (type) { "BALANCE" -> PaymentType.BALANCE else -> PaymentType.CREDIT },
            amount = amount,
            description = description,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt?.let { dateFormat.parse(createdAt!!) }.toString(),
            updatedAt = updatedAt?.let { dateFormat.parse(updatedAt!!) }.toString(),
            card = card?.asModel()
        )
    }
}