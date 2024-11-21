package pocket.pay.tp3_hci.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.ui.theme.Purple

@Composable
fun HomePageOptions(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Balance",
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )


        Text(
            text = "$50",
            fontSize = 32.sp,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = Color.White
                )
            ){
                Text("Deposit")
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = Color.White
                )
            ){
                Text("Alias/CBU")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomePageOptionsPreview(){
    HomePageOptions()
}
