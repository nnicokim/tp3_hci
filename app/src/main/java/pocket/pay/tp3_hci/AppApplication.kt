package pocket.pay.tp3_hci

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pocket.pay.tp3_hci.components.BottomBar
import pocket.pay.tp3_hci.ui.theme.PocketPayTheme
import pocket.pay.tp3_hci.navigations.AppNavGraph
import androidx.compose.runtime.getValue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppApplication() {
    PocketPayTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Scaffold (
            bottomBar = {
                BottomBar(
                    currentRoute = currentRoute
                ) { route ->
                    navController.navigate(route)  {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        ) {
            AppNavGraph(navController = navController)
        }
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun AppApplicationPreview() {
    AppApplication()
}