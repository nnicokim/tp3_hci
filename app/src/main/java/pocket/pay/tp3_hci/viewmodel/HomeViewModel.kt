package pocket.pay.tp3_hci.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _balance = MutableStateFlow(0f)
    val balance = _balance.asStateFlow()

    private val _alias = MutableStateFlow("John Doe")
    val alias: StateFlow<String> = _alias

    private val _cbu = MutableStateFlow("12345abcde")
    val cbu: StateFlow<String> = _cbu

    private val _recentTransactions = MutableStateFlow(listOf<String>())
    val recentTransactions: StateFlow<List<String>> = _recentTransactions

    fun addDeposit(amount: Float) {
        _balance.value += amount
        _recentTransactions.value = _recentTransactions.value + "Deposited: $${String.format("%.2f", amount)}"
    }

    fun withdraw(amount: Float): Boolean {
        return if (_balance.value >= amount) {
            _balance.value -= amount
            _recentTransactions.value = _recentTransactions.value + "Withdrew: $${String.format("%.2f", amount)}"
            true
        } else {
            false
        }
    }
}
