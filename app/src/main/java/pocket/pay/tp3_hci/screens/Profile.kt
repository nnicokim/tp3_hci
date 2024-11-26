package pocket.pay.tp3_hci.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import pocket.pay.tp3_hci.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.viewmodel.AccountViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(goToLogin: () -> Unit,
                  viewModel: AccountViewModel = viewModel(factory = AccountViewModel.provideFactory(
                      LocalContext.current.applicationContext as PocketPayApplication
                  )),
                  loggedOut: () -> Unit) {
    val configuration = LocalConfiguration.current
    val adaptiveInfo = currentWindowAdaptiveInfo()

    var showDialog by remember { mutableStateOf(false) }

    val uiState = viewModel.uiState

    Row {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Spacer(modifier = Modifier.width(80.dp))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewModel.getCurrentUser()
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = stringResource(id = R.string.user_profile),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "First Name: ${uiState.currentUser?.firstName}",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "Last Name: ${uiState.currentUser?.lastName}",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "Email: ${uiState.currentUser?.email}",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier.padding(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.logout))
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    title = {
                        Text(text = stringResource(id = R.string.are_you_sure))
                    },
                    text = {
                        Text(text = stringResource(id = R.string.logout_question))
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                viewModel.logoutExit(goToLogin)
                            }
                        ) {
                            Text("Accept")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showDialog = false
                            }
                        ) {
                            Text( text = stringResource(id = R.string.cancel))
                        }
                    }
                )
            }
        }
    }
}
