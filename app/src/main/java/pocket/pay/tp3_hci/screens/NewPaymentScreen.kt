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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.viewmodel.PaymentsViewModel

@Composable
fun NewPaymentScreen(
    goBackToPayment: () -> Unit,
    viewModel: PaymentsViewModel = viewModel()
) {
    val companyName by viewModel.companyName.collectAsState()
    val errorMessageCompanyName by viewModel.errorTextCompanyName.collectAsState()
    val paymentAmount by viewModel.paymentAmount.collectAsState()
    val errorMessagePaymentAmount by viewModel.errorTextPaymentAmount.collectAsState()
    val paymentSource by viewModel.paymentSource.collectAsState()
    val errorMessagePaymentSource by viewModel.errorTextPaymentSource.collectAsState()
    val date by viewModel.date.collectAsState()
    val errorTextDate by viewModel.errorTextDate.collectAsState()


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){

        Spacer(modifier = Modifier.height(80.dp))

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
            value = companyName,
            onValueChange = { viewModel.updateCompanyName(it) },
            label = { Text(stringResource(id = R.string.company_name)) },
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

            if (errorMessageCompanyName == "empty"){
                Text(
                    text = stringResource(R.string.empty_card_number),
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                )
            }


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
                onClick = {
                    viewModel.validateCompanyName(onValidCompanyName = {
                        goBackToPayment()
                    })
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