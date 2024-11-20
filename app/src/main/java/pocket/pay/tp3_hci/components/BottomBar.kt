package pocket.pay.tp3_hci.components

import androidx.compose.foundation.Image
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pocket.pay.tp3_hci.navigations.AppDestinations

@Composable
fun BottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit,
    contentColor: Color = Color(0xFFFFFFFF),
    containerColor: Color = Color(0xFF7382E5),
    selectedItemBackgroundColor: Color = Color(0XFF8F9BEA)
) {
    val items = listOf(
        AppDestinations.HOME,
        AppDestinations.PAYMENTS,
        AppDestinations.CARDS,
        AppDestinations.INVESTMENTS
    )

    NavigationBar (
        containerColor = containerColor,
        contentColor = contentColor
    ){
        items.forEach { destination ->
            NavigationBarItem(
                icon = {  DestinationIcon(destination, contentColor) },
                label = { Text (text = stringResource(destination.label),
                    color = contentColor)},
                alwaysShowLabel = true,
                selected = currentRoute == destination.route,
                onClick = { onNavigateToRoute(destination.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = contentColor,
                    selectedTextColor = contentColor,
                    indicatorColor = Color(0xFF8F9BEA)
                )
            )
        }
    }
}

@Composable
fun DestinationIcon(destination: AppDestinations, contentColor: Color) {
    Image(
        painter = painterResource(id = destination.icon),
        contentDescription = null,
        colorFilter = ColorFilter.tint(contentColor)
    )
}

@Preview
@Composable
fun BottomBarPreview(){
    BottomBar("home", onNavigateToRoute = {})
}
