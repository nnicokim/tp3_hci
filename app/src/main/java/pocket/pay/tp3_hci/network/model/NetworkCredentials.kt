package pocket.pay.tp3_hci.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCredentials (
    var email: String,
    var password: String
)