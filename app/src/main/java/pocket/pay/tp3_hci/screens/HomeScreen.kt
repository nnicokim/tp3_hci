package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.viewmodel.HomeViewModel
import androidx.compose.runtime.getValue

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(), goToMap : () -> Unit) {
    val balance by viewModel.balance.collectAsState()
    val recentTransactions by viewModel.recentTransactions.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Card {
            Text(
                text = stringResource(id = R.string.balance),
                fontSize = 30.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "$${String.format("%.2f", balance)}",
                fontSize = 45.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(27.dp))

        Row {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.DarkGray
                ),
                modifier = Modifier.wrapContentWidth()
                    .padding(horizontal = 10.dp),
            )

            {
                Icon(
                    painter = painterResource(id = R.drawable.dollar_sign_icon),
                    contentDescription = "Deposit"
                )
                Text(text = stringResource(id = R.string.deposit))
            }

            Spacer(modifier = Modifier.width(21.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.DarkGray
                ),
                modifier = Modifier.wrapContentWidth()
                    .padding(horizontal = 10.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.moneysign_icon),
                    contentDescription = "Withdrawal"
                )
                Text(text = stringResource(id = R.string.withdrawal))
            }

        }

        Spacer(modifier = Modifier.height(21.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.DarkGray
            ),
            modifier = Modifier.wrapContentWidth()
                .padding(horizontal = 10.dp),
        ){
            Icon(
                painter = painterResource(id = R.drawable.bank_icon),
                contentDescription = "Deposit"
            )
            Text(text = stringResource(id = R.string.alias))
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(id = R.string.map_text),
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                goToMap()
            },
            modifier = Modifier.wrapContentWidth()
                .padding(horizontal = 9.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(id = R.string.see_map),
                fontSize = 19.sp)
        }

        Spacer(
            modifier = Modifier.height(45.dp)
        )

        Card {
            Column {
                Text(
                    text = stringResource(id = R.string.recent_transactions),
                    fontSize = 20.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                recentTransactions.takeLast(2).forEach { // Solo mostramos las ultimas 2 transacciones
                    Text(
                        text = it,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen {  }
}