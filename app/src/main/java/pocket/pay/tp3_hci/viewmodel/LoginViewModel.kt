package pocket.pay.tp3_hci.viewmodel

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun validateAndLogin(
        onLoginSuccess: () -> Unit
    ) {
        if (_email.value.isBlank()) {
            _errorMessage.value = "Email cannot be empty"
        } else if (_password.value.isBlank()) {
            _errorMessage.value = "Password cannot be empty"
        } else if (!isEmailValid(_email.value)) {
            _errorMessage.value = "Invalid email format"
        } else {
            // Lógica de login
            onLoginSuccess()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
