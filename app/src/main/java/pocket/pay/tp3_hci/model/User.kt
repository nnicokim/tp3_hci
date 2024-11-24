package pocket.pay.tp3_hci.model

import java.util.Date

data class User(
    var id: Int?,
    var firstName: String,
    var lastName: String,
    var email: String,
    var birthDate: Date
)
