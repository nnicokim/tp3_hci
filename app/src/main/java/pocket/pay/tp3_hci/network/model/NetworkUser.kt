package pocket.pay.tp3_hci.network.model

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import pocket.pay.tp3_hci.model.User

@Serializable
class NetworkUser(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
) {
    fun asModel(): User {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))

        return User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            birthDate = birthDate.let { dateFormat.parse(birthDate) } as Date
        )
    }
}
