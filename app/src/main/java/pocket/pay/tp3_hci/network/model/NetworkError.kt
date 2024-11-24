package pocket.pay.tp3_hci.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkError (
    val message: String
)