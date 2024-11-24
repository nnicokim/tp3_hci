package pocket.pay.tp3_hci.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {

    private val _firstname = MutableStateFlow("")
    val firstname: StateFlow<String> = _firstname

    private val _lastname = MutableStateFlow("")
    val lastname: StateFlow<String> = _lastname

    private val _birthdate = MutableStateFlow("")
    val birthdate: StateFlow<String> = _birthdate

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

//    private val _confirmPassword = MutableStateFlow("")
//    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun updateFirstname(newName: String) {
        _firstname.value = newName
    }

    fun updateLastname(newName: String) {
        _lastname.value = newName
    }

    fun updateBirthdate(birthdate: String) {
        _birthdate.value = birthdate
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

//    fun updateConfirmPassword(newConfirmPassword: String) {
//        _confirmPassword.value = newConfirmPassword
//    }

    fun validateAndRegister(onLoginSuccess: () -> Unit, onError: (String) -> Unit) {
        if (_firstname.value.isBlank()) {
            onError("First name cannot be empty")
        } else if (_lastname.value.isBlank()) {
            onError("Last name cannot be empty")
        } else if (_birthdate.value.isBlank()) {
            onError("birth date cannot be empty")
        } else if (_email.value.isBlank()) {
            onError("Email cannot be empty")
        } else if (_password.value.isBlank()) {
            onError("Password field cannot be empty")
//        } else if (_password.value != _confirmPassword.value) {
//            onError("Passwords do not match")
        } else {
            // aca va la lógica de registro
            onLoginSuccess()
        }
    }
}
