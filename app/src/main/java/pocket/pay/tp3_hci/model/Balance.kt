package pocket.pay.tp3_hci.model

import pocket.pay.tp3_hci.network.model.NetworkBalance

data class Balance(
    var balance: Double
) {
    fun asNetworkModel(): NetworkBalance {
        return NetworkBalance(
            balance = balance
        )
    }
}
