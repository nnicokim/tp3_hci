package pocket.pay.tp3_hci.navigations

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import pocket.pay.tp3_hci.R

enum class AppDestinations (
    @StringRes val label: Int,
    //val icon: ImageVector,
    @DrawableRes val icon: Int,
    val route: String
) {
    HOME(label = R.string.home, icon = R.drawable.bank_icon, "home"),
    PAYMENTS(label = R.string.payments, icon = R.drawable.wallet_icon, "payments"),
    CARDS(label = R.string.cards, icon = R.drawable.card_icon, "cards"),
    INVESTMENTS(label = R.string.investments, icon = R.drawable.investment_icon, "investments")

//    HOME(label = R.string.home, Icons.Default.Home , "home"),
//    PAYMENTS(label = R.string.payments, icon = Icons.Default.Menu, "payments"),
//    CARDS(label = R.string.cards, icon = Icons.Default.Email, "cards"),
//    INVESTMENTS(label = R.string.investments, icon = Icons.Default.Share, "investments")
}


