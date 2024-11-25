package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.R

@Composable
fun InvestmentScreen(viewModel: InvestmentViewModel = viewModel()) {

    val investmentAmount by viewModel.investmentAmount.collectAsState()
    val errorText by viewModel.errorText.collectAsState()
    val isAddFundDialogVisible by viewModel.isAddFundDialogVisible.collectAsState()
    val isWithdrawDialogVisible by viewModel.isWithdrawDialogVisible.collectAsState()

    val configuration = LocalConfiguration.current  //Orientacion
    val adaptiveInfo = currentWindowAdaptiveInfo()

    Row {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(107.dp))

            Text(
                text = "Current Investment: $${String.format("%.2f", investmentAmount)}",
                fontSize = 33.sp,
                style = androidx.compose.ui.text.TextStyle(lineHeight = 40.sp)
            )

            Spacer(modifier = Modifier.height(33.dp))

            Text(
                text = stringResource(id = R.string.estimated_profit),
                fontSize = 21.sp,
                modifier = Modifier.padding(5.dp)
            )

            Text(
                text = "2,5%",
                fontSize = 23.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = { viewModel.showAddFundDialog() },
                modifier = Modifier.wrapContentWidth()
                    .padding(horizontal = 9.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.add_fund),
                    fontSize = 17.sp
                )
            }

            Spacer(modifier = Modifier.height(19.dp))

            Button(
                onClick = { viewModel.showWithdrawDialog() },
                modifier = Modifier.wrapContentWidth()
                    .padding(horizontal = 9.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.withdrawal),
                    fontSize = 17.sp
                )
            }

            if (errorText.isNotEmpty()) {
                Text(
                    text = errorText,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        if (isAddFundDialogVisible) {
            AddFundDialog(
                onDismiss = { viewModel.hideDialogs() },
                onConfirm = { amount -> viewModel.addFunds(amount) }
            )
        }

        if (isWithdrawDialogVisible) {
            WithdrawDialog(
                currentBalance = investmentAmount,
                onDismiss = { viewModel.hideDialogs() },
                onConfirm = { amount -> viewModel.withdrawFunds(amount) }
            )
        }
    }
}


@Composable
fun AddFundDialog(onDismiss: () -> Unit, onConfirm: (Float) -> Unit) {
    var amountText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Fund") },
        text = {
            Column {
                Text(text = stringResource(id = R.string.enter_invest),
                    fontSize = 17.sp)
                TextField(
                    value = amountText,
                    onValueChange = { amountText = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val amount = amountText.toFloatOrNull()
                if (amount != null && amount > 0) {
                    onConfirm(amount)
                    onDismiss()
                }
            }) {
                Text(text = stringResource(id = R.string.accept),
                    fontSize = 17.sp)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel),
                    fontSize = 17.sp)
            }
        }
    )
}

@Composable
fun WithdrawDialog(
    currentBalance: Float,
    onDismiss: () -> Unit,
    onConfirm: (Float) -> Unit
) {
    var amountText by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Withdraw Funds")},
        text = {
            Column {
                Text(text = stringResource(id = R.string.enter_withdraw),
                    fontSize = 17.sp)
                TextField(
                    value = amountText,
                    onValueChange = {
                        amountText = it
                        errorMessage = ""
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                val amount = amountText.toFloatOrNull()
                if (amount == null || amount <= 0) {
                    errorMessage = "Please enter a valid amount."
                } else if (amount > currentBalance) {
                    errorMessage = "Insufficient funds to complete the withdrawal."
                } else {
                    onConfirm(amount)
                    onDismiss()
                }
            }) {
                Text(text = stringResource(id = R.string.accept),
                    fontSize = 17.sp)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel),
                    fontSize = 17.sp)
            }
        }
    )
}


@PreviewScreenSizes
@Composable
fun InvestmentScreenPreview(){
    InvestmentScreen(InvestmentViewModel())
}
