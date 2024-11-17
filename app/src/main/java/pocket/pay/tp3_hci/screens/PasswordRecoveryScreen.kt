package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRecoveryScreen() {
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    Button(onClick = { /* falta implementar algo */ }) {
                        Text("←", fontSize = 25.sp) // cambiar por icono
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
                text = "Password recovery",
                fontSize = 35.sp,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 25.dp)
            )

            Text(
                text = "Please enter your email to recover your password",
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    // falta implementar
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Purple)
            ) {
                Text(text = "Accept",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 17.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordRecoveryScreenPreview() {
    PasswordRecoveryScreen()
}