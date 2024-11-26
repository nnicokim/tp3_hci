package pocket.pay.tp3_hci.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.ui.theme.Purple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.viewmodel.AccountViewModel


@Composable
fun HomeScreen(viewModel: AccountViewModel = viewModel(factory = AccountViewModel.provideFactory(
    LocalContext.current.applicationContext as PocketPayApplication
)), goToMap: () -> Unit) {

    val uiState = viewModel.uiState

    val alias by rememberSaveable { mutableStateOf("") }
    val cbu by rememberSaveable { mutableStateOf("")}

    var showDepositDialog by remember { mutableStateOf(false) }
    var showAliasDialog by remember { mutableStateOf(false) }
    var inputAmount by remember { mutableStateOf("") }

    val configuration = LocalConfiguration.current  // Orientacion
    val adaptiveInfo = currentWindowAdaptiveInfo()  // Tamaño de la pantalla

    Row {
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Spacer(modifier = Modifier.width(80.dp))
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){

                if(adaptiveInfo.windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT){
                    Row {
                        Column (
                            modifier = Modifier.width(400.dp)
                        ){
                            Card(
                                modifier = Modifier.padding(10.dp),
                                shape = RoundedCornerShape(30.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Purple,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    text = "Balance: ${uiState.currentBalance?.balance ?: 0.0}",
                                    fontSize = 45.sp,
                                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(bottom = 7.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(27.dp))

                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Button(
                                    onClick = { showDepositDialog = true },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White,
                                        contentColor = Color.Black
                                    ),
                                    modifier = Modifier
                                        .size(width = 130.dp, height = 100.dp)
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
                                    onClick = { showAliasDialog = true },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White,
                                        contentColor = Color.Black
                                    ),
                                    modifier = Modifier
                                        .size(width = 130.dp, height = 100.dp)
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
                            }
                        }
                        Column (
                            modifier = Modifier.fillMaxHeight()
                                .align(Alignment.CenterVertically),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Spacer(modifier = Modifier.height(10.dp))
                            Card(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {

                                Column {
                                    Text(
                                        text = stringResource(id = R.string.recent_transactions),
                                        fontSize = 20.sp,
                                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                        modifier = Modifier.padding(16.dp)
                                    )
//                                    viewModel.getPayments(
//                                        refresh = true
//                                    ).takeLast(2) // TODO: ver como obtener los pagos en algun tipo de lista
//                                        .forEach { // Solo mostramos las ultimas 2 transacciones
//                                            Text(
//                                                text = it,
//                                                modifier = Modifier.padding(8.dp),
//                                                fontSize = 16.sp
//                                            )
//                                        }
                                }
                            }

                            Spacer(modifier = Modifier.weight(1f))

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
                                Text(
                                    text = stringResource(id = R.string.see_map),
                                    fontSize = 19.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                } else {
                    Card(
                        modifier = Modifier.padding(10.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Purple,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.balance),
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "$${uiState.currentBalance}",
                            fontSize = 45.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 7.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(27.dp))

                    Row (
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(
                            onClick = { showDepositDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .size(width = 250.dp, height = 200.dp)
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
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = stringResource(id = R.string.deposit),
                                    fontSize = 20.sp
                                )
                            }
                        }

                        Button(
                            onClick = { showAliasDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .size(width = 250.dp, height = 200.dp)
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
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = stringResource(id = R.string.alias),
                                    fontSize = 20.sp
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
                        Text(
                            text = stringResource(id = R.string.see_map),
                            fontSize = 19.sp
                        )
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
//                            viewModel.getPayments(
//                                refresh = true
//                            ).takeLast(2)
//                                .forEach { // Solo mostramos las ultimas 2 transacciones
//                                    Text(
//                                        text = it,
//                                        modifier = Modifier.padding(8.dp),
//                                        fontSize = 16.sp
//                                    )
//                                }
                        }
                    }
                }
            } else if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                if(adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT){
                    Card(
                        modifier = Modifier.padding(10.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Purple,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.balance),
                            fontSize = 25.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            modifier = Modifier.padding(10.dp).padding(start = 15.dp)
                        )
                        Text(
                            text = "$${uiState.currentBalance}",
                            fontSize = 45.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 7.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(27.dp))

                    Row {
                        Button(
                            onClick = { showDepositDialog = true },
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
                            onClick = { showAliasDialog = true },
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

                    }
                } else {
                    Card(
                        modifier = Modifier.padding(10.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Purple,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.balance),
                            fontSize = 25.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            modifier = Modifier.padding(10.dp).padding(start = 15.dp)
                        )
                        Text(
                            text = "$${uiState.currentBalance}",
                            fontSize = 45.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 7.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(27.dp))

                    Row (
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(
                            onClick = { showDepositDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .size(width = 250.dp, height = 200.dp)
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
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = stringResource(id = R.string.deposit),
                                    fontSize = 20.sp
                                )
                            }
                        }

                        Button(
                            onClick = { showAliasDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .size(width = 250.dp, height = 200.dp)
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
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = stringResource(id = R.string.alias),
                                    fontSize = 20.sp
                                )
                            }
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
                    Text(
                        text = stringResource(id = R.string.see_map),
                        fontSize = 19.sp
                    )
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
//                        viewModel.getPayments(
//                            refresh = true
//                        ).takeLast(2)
//                            .forEach { // Solo mostramos las ultimas 2 transacciones
//                                Text(
//                                    text = it,
//                                    modifier = Modifier.padding(8.dp),
//                                    fontSize = 16.sp
//                                )
//                            }
                    }
                }
            }
            
            if (showDepositDialog) {
                AlertDialog(
                    onDismissRequest = { showDepositDialog = false },
                    title = { Text(text = stringResource(id = R.string.deposit)) },
                    text = {
                        Column {
                            Text(text = stringResource(id = R.string.enter_add))
                            TextField(
                                value = inputAmount,
                                onValueChange = { inputAmount = it },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                inputAmount.toDoubleOrNull()?.let { viewModel.recharge(amount = it) }
                                showDepositDialog = false
                                inputAmount = ""
                            }
                        ) {
                            Text(text = stringResource(id = R.string.accept))
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDepositDialog = false }) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                    }
                )
            }

            if (showAliasDialog) {
                AlertDialog(
                    onDismissRequest = { showAliasDialog = false },
                    title = { Text(text = "Alias and CBU") },
                    text = { Text(text = "Alias: $alias\nCBU: $cbu") },
                    confirmButton = {
                        Button(onClick = { showAliasDialog = false }) {
                            Text(text = stringResource(id = R.string.close))
                        }
                    }
                )
            }
        }
    }
}