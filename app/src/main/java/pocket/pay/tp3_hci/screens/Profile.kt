package pocket.pay.tp3_hci.screens

import android.content.res.Configuration
import android.widget.ImageView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pocket.pay.tp3_hci.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.viewmodel.AccountViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(goBackToHome: () -> Unit, goToLogin: () -> Unit,
            viewModel: AccountViewModel = viewModel(factory = AccountViewModel.provideFactory(
                LocalContext.current.applicationContext as PocketPayApplication
            )),
            loggedOut: () -> Unit) {

    val configuration = LocalConfiguration.current  // Orientacion
    val adaptiveInfo = currentWindowAdaptiveInfo()  // Tamaño de la pantalla

    val uiState = viewModel.uiState
    uiState.currentUser ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("  Profile") },
                navigationIcon = {
                    Button(
                        onClick = { goBackToHome() },
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.go_back_icon),
                            contentDescription = "Go back",
                            tint = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ProfileScreen(
            modifier = Modifier.padding(paddingValues),
            viewModel = viewModel,
            userFirstName = uiState.currentUser?.firstName ?: "",
            userLastName = uiState.currentUser?.lastName ?: "",
            userEmail = uiState.currentUser?.email ?: "",
            goToLogin = goToLogin,
        )
    }
}

@Composable
fun ProfileScreen(
    modifier: Modifier,
    viewModel: AccountViewModel,
    userFirstName: String,
    userLastName: String,
    userEmail: String,
    goToLogin: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val adaptiveInfo = currentWindowAdaptiveInfo()

    Row {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Spacer(modifier = Modifier.width(80.dp))
        }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Spacer(modifier = Modifier.height(25.dp))
            }
            Text(
                text = stringResource(id = R.string.user_profile),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "First Name: $userFirstName",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "Last Name: $userLastName",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "Email: $userEmail",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    viewModel.logoutExit(goToLogin)
                },
                modifier = Modifier.padding(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.logout))
            }
        }
    }
}
