package pocket.pay.tp3_hci.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pocket.pay.tp3_hci.navigations.AppDestinations
import pocket.pay.tp3_hci.screens.HomeScreen
import pocket.pay.tp3_hci.screens.LandingScreen

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
        items.forEach { item ->
            NavigationBarItem(
                icon = { item.icon },
                label = { item.label },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = { onNavigateToRoute(item.route) }
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(currentRoute = "payments",
        onNavigateToRoute = {
    })
}

