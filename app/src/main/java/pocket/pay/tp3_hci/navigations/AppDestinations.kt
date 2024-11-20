package pocket.pay.tp3_hci.navigations

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pocket.pay.tp3_hci.R

enum class AppDestinations (
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    HOME(label = R.string.home, icon = R.drawable.home_icon, "home"),
    PAYMENTS(label = R.string.payments, icon = R.drawable.wallet_icon, "payments"),
    CARDS(label = R.string.cards, icon = R.drawable.card_icon, "cards"),
    INVESTMENTS(label = R.string.investments, icon = R.drawable.investing_arrow_icon, "investments")
}


