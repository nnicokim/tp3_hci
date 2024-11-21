package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.R

@Composable
fun AddCardScreen(goBackToCards: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(65.dp))

        Text(
            text = stringResource(id = R.string.add_card),
            modifier = Modifier.padding(16.dp),
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(60.dp))

        Row {
            Button(
                onClick = {
                    goBackToCards()
                },
                modifier = Modifier.wrapContentWidth()
                    .padding(horizontal = 9.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD3D3D3),
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.cancel),
                    color = Color.Black,
                    fontSize = 19.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    // Falta implementar el agregado de la tarjeta
                    goBackToCards()
                },
                modifier = Modifier.wrapContentWidth()
                    .padding(horizontal = 9.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.save_card),
                    fontSize = 19.sp)
            }
        }
    }
}
