package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import pocket.pay.tp3_hci.ui.theme.Purple

@Composable
fun AddCardScreen(goBackToCards: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Add Card Screen",
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                goBackToCards()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD3D3D3),
                contentColor = Color.White
        )
        ) {
            Text("Cancel")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Falta implementar el agregado de la tarjeta
                goBackToCards()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                contentColor = Color.White
        )
        ) {
            Text("Save card")
        }
    }
}
