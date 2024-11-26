package pocket.pay.tp3_hci.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.model.Card
import pocket.pay.tp3_hci.model.CardType
import pocket.pay.tp3_hci.model.Payment
import pocket.pay.tp3_hci.model.PaymentType
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.viewmodel.AccountViewModel
import kotlin.random.Random

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewPaymentScreen(
    goBackToPayment: () -> Unit,
    viewModel: AccountViewModel = viewModel(factory = AccountViewModel.provideFactory(LocalContext.current.applicationContext as PocketPayApplication))
) {

    var id by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    var receiverEmail by rememberSaveable { mutableStateOf("") }

    var errorMessageId by remember { mutableStateOf<String?>(null) }
    var errorMessageDescription by remember { mutableStateOf<String?>(null) }
    var errorMessageAmount by remember { mutableStateOf<String?>(null) }
    var errorMessageReceiverEmail by remember { mutableStateOf<String?>(null) }

    val configuration = LocalConfiguration.current  //Orientacion
    val adaptiveInfo = currentWindowAdaptiveInfo()  //Tamaño de la pantalla

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = stringResource(id = R.string.new_payment),
            modifier = Modifier.padding(20.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(145.dp))


        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(stringResource(id = R.string.id)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(5.dp))

//        if (errorMessageCompanyName == "empty"){
//            Text(
//                text = stringResource(R.string.empty_company_name),
//                color = Color.Red,
//                style = MaterialTheme.typography.bodySmall,
//            )
//        }

        OutlinedTextField(
            value = description,
            onValueChange = {description = it },
            label = { Text(stringResource(id = R.string.description)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(5.dp))


//        if (errorMessagePaymentAmount == "invalid"){
//            Text(
//                text = stringResource(R.string.invalid_payment_amount),
//                color = Color.Red,
//                style = MaterialTheme.typography.bodySmall,
//            )
//        }

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text(stringResource(id = R.string.amount)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = receiverEmail,
            onValueChange = { receiverEmail = it },
            label = { Text(stringResource(id = R.string.receiver_email)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            )
        )

//        if (errorMessagePaymentSource.isNotEmpty()) {
//            if (errorMessagePaymentSource == "empty"){
//                Text(
//                    text = stringResource(R.string.empty_payment_source),
//                    color = Color.Red,
//                    style = MaterialTheme.typography.bodySmall,
//                )
//            } else {
//                Text(
//                    text = stringResource(R.string.invalid_payment_source),
//                    color = Color.Red,
//                    style = MaterialTheme.typography.bodySmall,
//                )
//            }
//        }

        Spacer(modifier = Modifier.height(40.dp))

        Row {
            Button(
                onClick = {
                    goBackToPayment()
                },
                modifier = Modifier
                    .wrapContentWidth()
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
                onClick =  //Agregar IF dependiendo del tipo de pago
                   {
                      viewModel.validateAndAddPayment(
                                    id = id.toInt(),
                                    description = description,
                                    amount = amount.toFloat(),
                                    receiverEmail = receiverEmail,
                                    onErrorId = { error -> errorMessageId = error },
                                    onErrorDescription = { error -> errorMessageDescription = error },
                                    onErrorAmount = { error -> errorMessageAmount = error },
                                    onErrorReceiverEmail = { error -> errorMessageReceiverEmail = error },
                                    goBackToPayment = { goBackToPayment() }
                                )
                    }
//                    viewModel.validateAndAddPayment(
//                        type = PaymentType.BALANCE,
//                        amount = paymentAmount.toDoubleOrNull(),
//                        description = paymentDescription,
//                        pending = false,
//                        onError = { errorMessage -> (errorMessage) },
//                        goBackToPayment = { goBackToPayment() }
//                    )
                ,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 9.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.pay),
                    fontSize = 19.sp
                )
            }
        }
    }
}

//@PreviewScreenSizes
//@Composable
//fun NewPaymentScreenPreview(){
//    NewPaymentScreen(goBackToPayment = {},
//        viewModel = PaymentsViewModel(),
//        homeViewModel = HomeViewModel()
//    )
//}