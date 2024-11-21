package pocket.pay.tp3_hci.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddFunds(amount: Double){
   // balance += amount

}

@Composable
fun AddFundsField(
    amount: Int
){
    val amount by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center

    ) {  }

    Text(
        text = "Type the amount you want to add to your wallet",
        fontSize = 10.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        modifier = Modifier.padding(5.dp)
    )

//    OutlinedTextField(
//        value = amount,
//        label = {Text( "Amount")},
//        modifier = Modifier.fillMaxWidth(),
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//    )

    TextButton(
        onClick = {

        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Forgot your password?", fontSize = 19.sp)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddFundsFieldPreview(){
//    AddFundsField(amount = )
//}