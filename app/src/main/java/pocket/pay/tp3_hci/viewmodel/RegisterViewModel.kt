package pocket.pay.tp3_hci.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _mobileNumber = MutableStateFlow("")
    val mobileNumber: StateFlow<String> = _mobileNumber

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    fun updateMobileNumber(newMobileNumber: String) {
        _mobileNumber.value = newMobileNumber
    }

    fun validateAndRegister(onLoginSuccess: () -> Unit, onError: (String) -> Unit) {
        if (_name.value.isBlank()) {
            onError("Name cannot be empty")
        } else if (_email.value.isBlank()) {
            onError("Email cannot be empty")
        } else if (_password.value.isBlank() || _confirmPassword.value.isBlank()) {
            onError("Password fields cannot be empty")
        } else if (_password.value != _confirmPassword.value) {
            onError("Passwords do not match")
        } else if (_mobileNumber.value.isBlank()) {
            onError("Mobile number cannot be empty")
        } else {
            // aca va la lógica de registro
            onLoginSuccess()
        }
    }
}
