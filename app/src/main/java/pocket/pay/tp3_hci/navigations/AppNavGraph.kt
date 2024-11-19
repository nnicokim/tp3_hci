package pocket.pay.tp3_hci.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pocket.pay.tp3_hci.screens.CardsScreen
import pocket.pay.tp3_hci.screens.HomeScreen
import pocket.pay.tp3_hci.screens.InvestmentsScreen
import pocket.pay.tp3_hci.screens.LandingScreen
import pocket.pay.tp3_hci.screens.LoginScreen
import pocket.pay.tp3_hci.screens.MapScreen
import pocket.pay.tp3_hci.screens.PasswordRecoveryScreen
import pocket.pay.tp3_hci.screens.PaymentsScreen
import pocket.pay.tp3_hci.screens.RegisterScreen
import androidx.compose.ui.Modifier
import pocket.pay.tp3_hci.screens.AddCardScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    isUserLoggedIn: Boolean,
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit
) {
    val startDestination = if (isUserLoggedIn) AppDestinations.HOME.route else "landing"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AppDestinations.HOME.route) {
            HomeScreen(goToMap = { navController.navigate("map") })
        }
        composable(route = AppDestinations.PAYMENTS.route) {
            PaymentsScreen()
        }
        composable(route = AppDestinations.CARDS.route) {
            CardsScreen(goToCreateCard = { navController.navigate("addcard") })
        }
        composable(route = AppDestinations.INVESTMENTS.route) {
            InvestmentsScreen()
        }
        composable(route = "landing") {
            LandingScreen(goToLogin = { navController.navigate("login") },
                goToRegister = { navController.navigate("register") })
        }
        composable(route = "login") {
            LoginScreen(onLoginSuccess = onLoginSuccess, onPasswordRecovery = { navController.navigate("passwordRecovery") },
                goToHome = { navController.navigate(
                    AppDestinations.HOME.route) },
                goToRegister = { navController.navigate("register") })
        }
        composable(route = "register") {
            RegisterScreen(onLoginSuccess = onLoginSuccess, goToHome = { navController.navigate(
                AppDestinations.HOME.route) })
        }
        composable(route = "passwordRecovery") {
            PasswordRecoveryScreen(onLoginSuccess = onLoginSuccess, goToHome = { navController.navigate(
                AppDestinations.HOME.route) })
        }
        composable(route = "map") {
            MapScreen(goBackToHome = { navController.navigate(AppDestinations.HOME.route) })
        }
        composable(route = "addcard") {
            AddCardScreen(goBackToCards = { navController.navigate(AppDestinations.CARDS.route) })
        }
    }
}

