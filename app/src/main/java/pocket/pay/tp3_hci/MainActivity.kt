package pocket.pay.tp3_hci

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import pocket.pay.tp3_hci.components.BottomBar
import pocket.pay.tp3_hci.components.Header
import pocket.pay.tp3_hci.components.SideBar
import pocket.pay.tp3_hci.navigations.AppDestinations
import pocket.pay.tp3_hci.navigations.AppNavGraph
import pocket.pay.tp3_hci.ui.theme.PocketPayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocketPayTheme {
                PocketPayApp()
            }
        }
    }
}

@Composable
fun PocketPayApp() {
    val navController = rememberNavController()
    var isUserLoggedIn by remember { mutableStateOf(false) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val configuration = LocalConfiguration.current
    val adaptiveInfo = currentWindowAdaptiveInfo()

    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
    Scaffold(
        topBar = {
            if (currentRoute in listOf(
                    AppDestinations.HOME.route,
                    AppDestinations.PAYMENTS.route,
                    AppDestinations.CARDS.route,
                    AppDestinations.INVESTMENTS.route
                )
            ) {
                Header(
                    username = "John Doe", // TODO: ver como obtener el nombre de usuario
                    modifier = Modifier,
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (currentRoute in listOf(
                    AppDestinations.HOME.route,
                    AppDestinations.PAYMENTS.route,
                    AppDestinations.CARDS.route,
                    AppDestinations.INVESTMENTS.route
                )
            ) {
                BottomBar(
                    currentRoute = currentRoute,
                    onNavigateToRoute = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            isUserLoggedIn = isUserLoggedIn,
            modifier = Modifier.padding(innerPadding),
            onLoginSuccess = { isUserLoggedIn = true },
            loggedOut = { isUserLoggedIn = false }
        )
    }
    } else {
        Scaffold(
            bottomBar = {
                if (currentRoute in listOf(
                        AppDestinations.HOME.route,
                        AppDestinations.PAYMENTS.route,
                        AppDestinations.CARDS.route,
                        AppDestinations.INVESTMENTS.route
                    )
                ) {
                    SideBar(
                        currentRoute = currentRoute,
                        onNavigateToRoute = { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            },
            topBar = {
                if (currentRoute in listOf(
                        AppDestinations.HOME.route,
                        AppDestinations.PAYMENTS.route,
                        AppDestinations.CARDS.route,
                        AppDestinations.INVESTMENTS.route
                    )
                ) {
                    Header(
                        username = "John Doe", // TODO: ver como obtener el nombre de usuario
                        modifier = Modifier,
                        navController = navController
                    )
                }
            }
        ) { innerPadding ->
            AppNavGraph(
                navController = navController,
                isUserLoggedIn = isUserLoggedIn,
                modifier = Modifier.padding(innerPadding),
                onLoginSuccess = { isUserLoggedIn = true },
                loggedOut = { isUserLoggedIn = false }
            )
        }
    }
}
