package pocket.pay.tp3_hci.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InvestmentViewModel : ViewModel() {
    private val _investmentAmount = MutableStateFlow(0f)
    val investmentAmount = _investmentAmount.asStateFlow()

    private val _chartData = MutableStateFlow<List<Float>>(listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f)) // Los 7 dias de la semana
    val chartData = _chartData.asStateFlow()

    private val _errorText = MutableStateFlow("")
    val errorText = _errorText.asStateFlow()

    private val _isAddFundDialogVisible = MutableStateFlow(false)
    val isAddFundDialogVisible = _isAddFundDialogVisible.asStateFlow()

    private val _isWithdrawDialogVisible = MutableStateFlow(false)
    val isWithdrawDialogVisible = _isWithdrawDialogVisible.asStateFlow()

    fun showAddFundDialog() {
        _isAddFundDialogVisible.value = true
    }

    fun showWithdrawDialog() {
        _isWithdrawDialogVisible.value = true
    }

    fun hideDialogs() {
        _isAddFundDialogVisible.value = false
        _isWithdrawDialogVisible.value = false
        _errorText.value = ""
    }

    fun addFunds(amount: Float) {
        _investmentAmount.value += amount
        updateChartData()
    }

    fun withdrawFunds(amount: Float) {
        if (amount > _investmentAmount.value) {
            _errorText.value = "Cannot withdraw more than invested amount!"
        } else {
            _investmentAmount.value -= amount
            _errorText.value = ""
            updateChartData()
        }
    }

    private fun updateChartData() {
        val newData = _chartData.value.toMutableList()
        newData.add(_investmentAmount.value)
        if (newData.size > 7) newData.removeAt(0)
        _chartData.value = newData
    }
}

