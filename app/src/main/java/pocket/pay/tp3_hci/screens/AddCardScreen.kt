package pocket.pay.tp3_hci.screens

import android.content.res.Configuration
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowHeightSizeClass
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.viewmodel.CardsViewModel
import kotlin.math.round


@Composable
fun AddCardScreen(
    goBackToCards: () -> Unit,
    viewModel: CardsViewModel = viewModel()
) {
    val number by viewModel.cardNumber.collectAsState()
    val errorMessageCardNumber by viewModel.errorTextCardNumber.collectAsState()
    val name by viewModel.cardholderName.collectAsState()
    val errorMessageCardName by viewModel.errorTextCardName.collectAsState()
    val date by viewModel.expiryDate.collectAsState()
    val errorMessageExpDate by viewModel.errorTextCardExpDate.collectAsState()
    val cvv by viewModel.cvv.collectAsState()
    val errorMessage by viewModel.errorTextCardCVV.collectAsState()

    val configuration = LocalConfiguration.current  //Orientacion
    val adaptiveInfo = currentWindowAdaptiveInfo()  //Tamaño de la pantalla

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE && adaptiveInfo.windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT){
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.add_card),
                modifier = Modifier.padding(20.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
        }
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(80.dp))

                OutlinedTextField(
                    value = number,
                    onValueChange = { viewModel.updateCardNumber(it) },
                    label = { Text(stringResource(id = R.string.add_card_number)) },
                    modifier = Modifier.fillMaxWidth().
                    height(65.dp).padding(5.dp),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                if (errorMessageCardNumber.isNotEmpty()) {
                    if (errorMessageCardNumber == "empty"){
                        Text(
                            text = stringResource(R.string.empty_card_number),
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.invalid_card_number),
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }

                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { viewModel.updateCardName(it) },
                    label = { Text("Cardholder's name:") },
                    modifier = Modifier.fillMaxWidth().
                    height(65.dp).padding(5.dp),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                if (errorMessageCardName.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.empty_card_name),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(80.dp))

                OutlinedTextField(
                    value = date,
                    onValueChange = { viewModel.updateCardExpDate(it) },
                    label = { Text(stringResource(id = R.string.add_card_exp_date)) },
                    modifier = Modifier.fillMaxWidth().
                    height(65.dp).padding(5.dp),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                if (errorMessageExpDate.isNotEmpty()) {
                    if (errorMessageExpDate == "empty"){
                        Text(
                            text = stringResource(R.string.empty_card_exp_date),
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.invalid_card_exp_date),
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }

                OutlinedTextField(
                    value = cvv,
                    onValueChange = { viewModel.updateCardCvv(it) },
                    label = { Text(stringResource(id = R.string.add_card_cvv)) },
                    modifier = Modifier.fillMaxWidth().
                    height(65.dp).padding(5.dp),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                if (errorMessage.isNotEmpty()) {
                    if (errorMessage == "empty"){
                        Text(
                            text = stringResource(R.string.empty_card_cvv),
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.invalid_card_cvv),
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }

                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(280.dp))

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
                        viewModel.isCardDataValid(onValidCard = {
                            goBackToCards()
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
                        text = stringResource(id = R.string.continue_string),
                        fontSize = 19.sp
                    )
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(id = R.string.add_card),
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
            Spacer(modifier = Modifier.height(125.dp))


            OutlinedTextField(
                value = number,
                onValueChange = { viewModel.updateCardNumber(it) },
                label = { Text(stringResource(id = R.string.add_card_number)) },
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

            if (errorMessageCardNumber.isNotEmpty()) {
                if (errorMessageCardNumber == "empty"){
                    Text(
                        text = stringResource(R.string.empty_card_number),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                    )
                } else {
                    Text(
                        text = stringResource(R.string.invalid_card_number),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

            }

            OutlinedTextField(
                value = name,
                onValueChange = { viewModel.updateCardName(it) },
                label = { Text("Cardholder's name:") },
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

            if (errorMessageCardName.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.empty_card_name),
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            OutlinedTextField(
                value = date,
                onValueChange = { viewModel.updateCardExpDate(it) },
                label = { Text(stringResource(id = R.string.add_card_exp_date)) },
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

            if (errorMessageExpDate.isNotEmpty()) {
                if (errorMessageExpDate == "empty"){
                    Text(
                        text = stringResource(R.string.empty_card_exp_date),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                    )
                } else {
                    Text(
                        text = stringResource(R.string.invalid_card_exp_date),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            OutlinedTextField(
                value = cvv,
                onValueChange = { viewModel.updateCardCvv(it) },
                label = { Text(stringResource(id = R.string.add_card_cvv)) },
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

            if (errorMessage.isNotEmpty()) {
                if (errorMessage == "empty"){
                    Text(
                        text = stringResource(R.string.empty_card_cvv),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                    )
                } else {
                    Text(
                        text = stringResource(R.string.invalid_card_cvv),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

            }

            Spacer(modifier = Modifier.height(40.dp))

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
                        viewModel.isCardDataValid(onValidCard = {
                            goBackToCards()
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
                        text = stringResource(id = R.string.continue_string),
                        fontSize = 19.sp
                    )
                }
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun AddCardScreenPreview(){
    AddCardScreen(
        goBackToCards = {},
        viewModel = CardsViewModel()
    )
}