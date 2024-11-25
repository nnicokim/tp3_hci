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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.model.CardType
import pocket.pay.tp3_hci.model.PaymentType
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.viewmodel.AccountViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewPaymentScreen(
    goBackToPayment: () -> Unit,
    viewModel: AccountViewModel = viewModel(factory = AccountViewModel.provideFactory(LocalContext.current.applicationContext as PocketPayApplication))
) {
//    val companyName by viewModel.companyName.collectAsState()
//    val errorMessageCompanyName by viewModel.errorTextCompanyName.collectAsState()
//    val paymentAmount by viewModel.paymentAmount.collectAsState()
//    val errorMessagePaymentAmount by viewModel.errorTextPaymentAmount.collectAsState()
//    val paymentSource by viewModel.paymentSource.collectAsState()
//    val errorMessagePaymentSource by viewModel.errorTextPaymentSource.collectAsState()

    var paymentDescription by rememberSaveable { mutableStateOf("")}
    var paymentAmount by rememberSaveable { mutableStateOf("")}
    var paymentSource by rememberSaveable { mutableStateOf("")}

    val balance = viewModel.getBalance()

    val configuration = LocalConfiguration.current  //Orientacion
    val adaptiveInfo = currentWindowAdaptiveInfo()  //Tamaño de la pantalla

    Column(
        modifier = Modifier.fillMaxSize()
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
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(145.dp))


        OutlinedTextField(
            value = paymentDescription,
            onValueChange = { paymentDescription = it },
            label = { Text(stringResource(id = R.string.company_name)) },
            modifier = Modifier.fillMaxWidth().
            height(65.dp),
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
            value = paymentAmount,
            onValueChange = {paymentAmount = it },
            label = { Text(stringResource(id = R.string.payment_amount)) },
            modifier = Modifier.fillMaxWidth().
            height(65.dp),
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

        Text(
            text = "$balance",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
        )

        OutlinedTextField(
            value = paymentSource,
            onValueChange = { paymentSource = it },
            label = { Text(stringResource(id = R.string.payment_source)) },
            modifier = Modifier.fillMaxWidth().
            height(65.dp),
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
                onClick = { //Agregar IF dependiendo del tipo de pago
                    viewModel.validateAndAddPayment(
                        type = PaymentType.BALANCE,
                        amount = paymentAmount.toFloat(),
                        description = paymentDescription,
                        pending = false,
                        onError = { errorMessage -> (errorMessage) },
                        goBackToPayment = { goBackToPayment() }
                    )
                },
                modifier = Modifier.wrapContentWidth()
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