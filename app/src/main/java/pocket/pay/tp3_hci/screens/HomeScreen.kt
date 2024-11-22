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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Card (
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(40.dp),
            colors = CardDefaults.cardColors(
                containerColor = Purple, // Background color
                contentColor = Color.White // Content color
            )
        ){
            Text(
                text = stringResource(id = R.string.balance),
                fontSize = 25.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier.padding(10.dp).padding(start = 15.dp)
            )
            Text(
                text = "$${String.format("%.2f", balance)}",
                fontSize = 45.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 7.dp)
            )
        }

        Spacer(modifier = Modifier.height(27.dp))

        Row {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 2.dp,
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.dollar_sign_icon),
                        contentDescription = "Deposit",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(id = R.string.deposit),
                        fontSize = 12.sp
                    )
                }
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 2.dp,
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bank_icon),
                        contentDescription = "alias",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(id = R.string.alias),
                        fontSize = 12.sp
                    )
                }
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 2.dp,
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.moneysign_icon),
                        contentDescription = "withdrawal",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(id = R.string.withdrawal),
                        fontSize = 12.sp
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(30.dp))

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
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(text = stringResource(id = R.string.see_map),
                fontSize = 19.sp)
        }

        Spacer(
            modifier = Modifier.height(45.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen {  }
}