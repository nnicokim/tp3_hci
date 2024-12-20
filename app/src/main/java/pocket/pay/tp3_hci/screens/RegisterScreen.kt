package pocket.pay.tp3_hci.screens

import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.navigations.AppDestinations
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(onLoginSuccess: () -> Unit, goToHome: () -> Unit, goToLogin : () -> Unit,
                   viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val firstname by viewModel.firstname.collectAsState()
    val lastname by viewModel.lastname.collectAsState()
    val birthdate by viewModel.birthdate.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    // val confirmPassword by viewModel.confirmPassword.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign ",
                color = Color.Black,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "up",
                color = Purple,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(17.dp))

        Text(
            text = stringResource(id = R.string.register_text),
            color = Color.Gray,
            fontSize = 23.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = firstname,
            onValueChange = { viewModel.updateFirstname(it) },
            label = { Text("First name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = lastname,
            onValueChange = { viewModel.updateLastname(it) },
            label = { Text("Last name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = birthdate,
            onValueChange = { viewModel.updateBirthdate(it) },
            label = { Text("Birth date") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.updatePassword(it) },
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
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        )

//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = confirmPassword,
//            onValueChange = { viewModel.updateConfirmPassword(it) },
//            label = { Text("Confirm Password") },
//            modifier = Modifier.fillMaxWidth(),
//            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = {
//                val icon = if (isPasswordVisible) {
//                    painterResource(id = R.drawable.ic_visibility_off)
//                } else {
//                    painterResource(id = R.drawable.ic_visibility)
//                }
//
//                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
//                    Icon(
//                        painter = icon,
//                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
//                        modifier = Modifier.size(24.dp)
//                    )
//                }
//            }
//        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.validateAndRegister(
                    onLoginSuccess = {
                        onLoginSuccess()
                        goToHome()
                    },
                    onError = { "Error" }
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Purple, contentColor = Color.White)
        ) {
            Text(text = stringResource(id = R.string.create_my_account),
                fontSize = 19.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.account_question),
            color = Color.Gray,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(3.dp))

        TextButton(
            onClick = {
                goToLogin()
            }
        ) {
            Text(
                text = stringResource(id = R.string.go_to_login),
                color = Purple,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onLoginSuccess = {},
        goToHome = {},
        goToLogin = {})
}





