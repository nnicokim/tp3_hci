package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.navigations.AppDestinations
import pocket.pay.tp3_hci.viewmodel.AccountViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyScreen(onLoginSuccess: () -> Unit, goToLogin: () -> Unit,
                 viewModel: AccountViewModel = viewModel(factory = AccountViewModel.provideFactory(
                     LocalContext.current.applicationContext as PocketPayApplication
                 ))
) {

    var code by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    Button(onClick = { /* falta implementar algo */ },
                        modifier = Modifier
                            .size(48.dp)
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
                            modifier = Modifier.size(29.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Verify email with code",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 27.dp)
            )

            Text(
                text = "Please enter your code to verify email",
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                label = { Text("Code") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    viewModel.verify(code)
                    goToLogin()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Purple)
            ) {
                Text(text = "Accept",
                    color = Color.White,
                    fontSize = 17.sp)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PasswordRecoveryScreenPreview() {
//    PasswordRecoveryScreen(onLoginSuccess = {}, goToHome = {})
//}