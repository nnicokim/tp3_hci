package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.ui.theme.Purple

@Composable
fun LandingScreen(goToLogin : () -> Unit, goToRegister : () -> Unit) {

    val configuration = LocalConfiguration.current  // Orientacion
    val adaptiveInfo = currentWindowAdaptiveInfo()  // Tamaño de la pantalla

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(77.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pplogo),
                contentDescription = "Logo",
                modifier = Modifier.size(85.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "Pocket ",
                color = Color.Black,
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Pay",
                color = Purple,
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Your entire wallet in your pocket",
            color = Color.Gray,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.signin_create),
            color = Color.Black,
            fontSize = 23.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                goToLogin()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(id = R.string.signin),
                fontSize = 17.sp,)
        }

        Spacer(modifier = Modifier.height(17.dp))

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
            Text(stringResource(id = R.string.register),
                color = Color.White,
                fontSize = 17.sp,)
        }
    }
}

@PreviewScreenSizes
@Composable
fun LandingScreenPreview() {
    LandingScreen(goToLogin = {}, goToRegister = {})
}