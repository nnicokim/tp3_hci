package pocket.pay.tp3_hci.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pocket.pay.tp3_hci.screens.CardsScreen
import pocket.pay.tp3_hci.screens.HomeScreen
import pocket.pay.tp3_hci.screens.InvestmentsScreen
import pocket.pay.tp3_hci.screens.PaymentsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,

        // TODO: modificar para que si esta logueado muestre el home y sino el LandingScreen
        startDestination = "AppDestinations.HOME.route"
    ) {
        composable(route = AppDestinations.HOME.route) {
            HomeScreen()
        }
        composable(route = AppDestinations.PAYMENTS.route) {
            PaymentsScreen()
        }
        composable(route = AppDestinations.CARDS.route) {
            CardsScreen()
        }
        composable(route = AppDestinations.INVESTMENTS.route) {
            InvestmentsScreen()
        }
    }
}

