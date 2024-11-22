package pocket.pay.tp3_hci.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _balance = MutableStateFlow(50f)
    val balance: StateFlow<Float> = _balance

    private val _recentTransactions = MutableStateFlow(listOf<String>())
    val recentTransactions: StateFlow<List<String>> = _recentTransactions

    fun addTransaction(amount: Float, description: String) {
        _balance.value += amount
        _recentTransactions.value = _recentTransactions.value + description
    }
}