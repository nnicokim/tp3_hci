package pocket.pay.tp3_hci.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pocket.pay.tp3_hci.screens.CardsScreen
import pocket.pay.tp3_hci.screens.HomeScreen
//import pocket.pay.tp3_hci.screens.InvestmentScreen
import pocket.pay.tp3_hci.screens.LandingScreen
import pocket.pay.tp3_hci.screens.LoginScreen
import pocket.pay.tp3_hci.screens.MapScreen
import pocket.pay.tp3_hci.screens.PasswordRecoveryScreen
import pocket.pay.tp3_hci.screens.PaymentsScreen
import pocket.pay.tp3_hci.screens.RegisterScreen
import androidx.compose.ui.Modifier
import pocket.pay.tp3_hci.screens.AddCardScreen
//import pocket.pay.tp3_hci.screens.InvestmentScreen
import pocket.pay.tp3_hci.screens.NewPaymentScreen
import pocket.pay.tp3_hci.screens.Profile
import pocket.pay.tp3_hci.screens.VerifyScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    isUserLoggedIn: Boolean,
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
    loggedOut: () -> Unit
) {
    val startDestination = if (isUserLoggedIn) AppDestinations.HOME.route else "landing"

  //  val investmentViewModel = InvestmentViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AppDestinations.HOME.route) {
            HomeScreen(goToMap = { navController.navigate("map") })
        }
        composable(route = AppDestinations.PAYMENTS.route) {
            PaymentsScreen(goToNewPayment = {navController.navigate("newpayment")})
        }
        composable(route = AppDestinations.CARDS.route) {
            CardsScreen(goToCreateCard = { navController.navigate("addcard") })
        }
        composable(route = "verify"){
            VerifyScreen(onLoginSuccess = onLoginSuccess, goToLogin = {navController.navigate("login")})
        }
//        composable(route = AppDestinations.INVESTMENTS.route) {
//            InvestmentScreen(investmentViewModel)
//        }
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
            RegisterScreen(onLoginSuccess = onLoginSuccess, goToLogin = {navController.navigate("login")},goToVerify = { navController.navigate("verify") })
        }
        composable(route = "passwordRecovery") {
            PasswordRecoveryScreen(onLoginSuccess = onLoginSuccess, goToHome = { navController.navigate(
                AppDestinations.HOME.route) })
        }
        composable(route = "map") {
            MapScreen(goBackToHome = { navController.navigate(AppDestinations.HOME.route) })
        }
        composable(route = "addcard") {
            AddCardScreen(
                goBackToCards = { navController.navigate(AppDestinations.CARDS.route) },
            )
        }

        composable(route = "newpayment"){
            NewPaymentScreen(
                goBackToPayment = { navController.navigate(AppDestinations.PAYMENTS.route) }
            )
        }

        composable(route = "profile") {
            Profile(goBackToHome = { navController.navigate(AppDestinations.HOME.route) },
                goToLogin = { navController.navigate("login") },
                loggedOut = loggedOut)
        }

    }
}


