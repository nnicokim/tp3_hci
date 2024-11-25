package pocket.pay.tp3_hci.components

import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.navigations.AppDestinations


@Composable
    fun SideBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit,
    contentColor: Color = Color(0xFFFFFFFF),
    containerColor: Color = Color(0xFF7382E5)
    ) {
        val items = listOf(
            AppDestinations.HOME,
            AppDestinations.PAYMENTS,
            AppDestinations.CARDS,
            AppDestinations.PROFILE
        )

        NavigationRail(
            containerColor = containerColor,
            contentColor = contentColor
        ) {
            items.forEach { destination ->
                NavigationRailItem(
                    selected = currentRoute == destination.route,
                    onClick = { onNavigateToRoute(destination.route) },
                    icon = { DestinationIcon(destination, contentColor) },
                    label = { Text(stringResource(destination.label), color = contentColor) },
                    alwaysShowLabel = true,
                    colors = NavigationRailItemDefaults.colors(
                        selectedIconColor = contentColor,
                        selectedTextColor = contentColor,
                        indicatorColor = Color(0xFF8F9BEA)
                    )
                )
            }
        }
    }

@PreviewScreenSizes
@Composable
fun SideBarPreview(){
    SideBar("home", onNavigateToRoute = {})
}
