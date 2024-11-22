package pocket.pay.tp3_hci.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    // Datos del usuario (de ejemplo)
    var userName by mutableStateOf("John Doe")
    var userEmail by mutableStateOf("johndoe@example.com")
    var userPhone by mutableStateOf("+1234567890")

    fun getUserProfile() {
        // TODO: getter (Ver como levantar los datos de la API)
    }
}
