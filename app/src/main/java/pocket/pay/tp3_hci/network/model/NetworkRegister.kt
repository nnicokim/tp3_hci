package pocket.pay.tp3_hci.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkRegister(
    var firstName: String,
    var lastName: String,
    var email: String,
    var birthDate: String,
    var password: String
)
