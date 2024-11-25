package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import pocket.pay.tp3_hci.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.viewmodel.AccountViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onPasswordRecovery: () -> Unit,
                goToHome: () -> Unit,
                goToRegister: () -> Unit,
                viewModel: AccountViewModel) {

    var email by rememberSaveable { mutableStateOf("")}
    var password by rememberSaveable { mutableStateOf("")}

    val configuration = LocalConfiguration.current  // Orientacion
    val adaptiveInfo = currentWindowAdaptiveInfo()  // Tamaño de la pantalla

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pplogo),
                contentDescription = "Logo",
                modifier = Modifier.size(90.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "Pocket ",
                color = Color.Black,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Pay",
                color = Purple,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Your entire wallet in your pocket",
            color = Color.Gray,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordInputField(
            password = password,
            onPasswordChange = { password = it }
        )

        Spacer(modifier = Modifier.height(27.dp))

        Button(
            onClick = {
                viewModel.validateAndLogin(
                    email = email,
                    password = password,
                    onError = { errorMessage -> (errorMessage) },
                    goToHome = { goToHome() }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login", fontSize = 17.sp)
        }

        Spacer(modifier = Modifier.height(13.dp))

        Button(
            onClick = {
                goToRegister()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                contentColor = Color.White
            )
        ) {
            Text(text = "Register", fontSize = 17.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                onPasswordRecovery()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Forgot your password?", fontSize = 19.sp)
        }
    }
}



@Composable
fun PasswordInputField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    // Variable de estado para controlar si la contraseña se muestra u oculta
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if (isPasswordVisible) {
                painterResource(id = R.drawable.ic_visibility_off)
            } else {
                painterResource(id = R.drawable.ic_visibility)
            }

            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = icon,
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}




