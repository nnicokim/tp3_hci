package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.viewmodel.InvestmentViewModel

@Composable
fun InvestmentScreen(viewModel: InvestmentViewModel = viewModel()) {
    val investmentAmount by viewModel.investmentAmount.collectAsState()
    val errorText by viewModel.errorText.collectAsState()
    val isAddFundDialogVisible by viewModel.isAddFundDialogVisible.collectAsState()
    val isWithdrawDialogVisible by viewModel.isWithdrawDialogVisible.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(105.dp))

        Text(text = "Current Investment: $${String.format("%.2f", investmentAmount)}",
            fontSize = 33.sp,
            style = androidx.compose.ui.text.TextStyle(lineHeight = 40.sp))

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = stringResource(id = R.string.estimated_profit),
            fontSize = 19.sp,
            modifier = Modifier.padding(5.dp))

        Text(text = "2,5%",
            fontSize = 21.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier.padding(8.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.showAddFundDialog() },
            modifier = Modifier.wrapContentWidth()
                .padding(horizontal = 9.dp),
        ) {
            Text(text = stringResource(id = R.string.add_fund),
                fontSize = 17.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.showWithdrawDialog() },
            modifier = Modifier.wrapContentWidth()
                .padding(horizontal = 9.dp),
        ) {
            Text(text = stringResource(id = R.string.withdrawal),
                fontSize = 17.sp)
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


@Composable
fun AddFundDialog(onDismiss: () -> Unit, onConfirm: (Float) -> Unit) {
    var amountText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Fund") },
        text = {
            Column {
                Text(text = "Enter amount to invest:")
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
                Text(text = stringResource(id = R.string.accept))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel))
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
        title = { Text("Withdraw Funds") },
        text = {
            Column {
                Text(text = "Enter amount to withdraw:")
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
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    )
}

