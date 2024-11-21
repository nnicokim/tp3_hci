package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.R


@Composable
fun CardsScreen(goToCreateCard : () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.cards_title),
            modifier = Modifier.padding(20.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.no_cards),
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Agregar el condicional si NO hay tarjetas
        Button(
            onClick = {
                goToCreateCard()
            },
            modifier = Modifier.wrapContentWidth()
                .padding(horizontal = 9.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(id = R.string.add_card),
                fontSize = 19.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardsScreenPreview() {
    CardsScreen { }
}