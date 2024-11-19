package pocket.pay.tp3_hci.components

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import pocket.pay.tp3_hci.navigations.AppDestinations

@Composable
fun BottomBar( currentRoute: String?,
                   onNavigateToRoute: (String) -> Unit) {
    val items = listOf(
        AppDestinations.HOME,
        AppDestinations.PAYMENTS,
        AppDestinations.CARDS,
        AppDestinations.INVESTMENTS
    )

    NavigationBar {
        items.forEach { destination ->
            NavigationBarItem(
                icon = {  DestinationIcon(destination) },
                label = { Text (text = stringResource(destination.label) )},
                alwaysShowLabel = true,
                selected = currentRoute == destination.route,
                onClick = { onNavigateToRoute(destination.route) }
            )
        }
    }
}

@Composable
fun DestinationIcon(destination: AppDestinations) {
    Image(
        painter = painterResource(id = destination.icon),
        contentDescription = null
    )
}
