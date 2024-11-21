package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pocket.pay.tp3_hci.R
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource
import pocket.pay.tp3_hci.ui.theme.Purple
import kotlin.Int


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(goBackToHome : () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("  Profile") },
                navigationIcon = {
                    Button(onClick = { goBackToHome() },
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
                })
        }
    ) { paddingValues ->
        ProfileScreen(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun ProfileScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = R.string.user_profile),
            modifier = Modifier.padding(20.dp)
        )

        Button (onClick = { /*TODO*/ }, // TODO: LOGOUT
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