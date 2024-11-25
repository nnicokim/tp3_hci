package pocket.pay.tp3_hci.screens

import android.content.res.Configuration
import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowHeightSizeClass
import pocket.pay.tp3_hci.PocketPayApplication
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.ui.theme.Purple
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.navigations.AppNavGraph
import pocket.pay.tp3_hci.viewmodel.AccountViewModel

@Composable
fun CardsScreen(
    goToCreateCard: () -> Unit,
    viewModel: AccountViewModel = viewModel(factory = AccountViewModel.provideFactory(LocalContext.current.applicationContext as PocketPayApplication))

) {
    val uiState = viewModel.uiState
    val configuration = LocalConfiguration.current
    val adaptiveInfo = currentWindowAdaptiveInfo()

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Spacer(modifier = Modifier.width(80.dp))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                Spacer(modifier = Modifier.height(80.dp))
                Row {
                    Text(
                    text = stringResource(id = R.string.cards_title),
                    modifier = Modifier.padding(20.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            goToCreateCard()
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
                            text = stringResource(id = R.string.add_card),
                            fontSize = 19.sp
                        )
                    }

                }


            }

            if (adaptiveInfo.windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    // Mostramos las tarjetas dinamicamente
                    if (uiState.cards != null) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2), // Set to 2 cards per row
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)
                        ) {
                            items(uiState.cards){
                                    card ->
                                Box(
                                    modifier = Modifier
                                        .width(320.dp)
                                        .height(100.dp)
                                        .background(
                                            color = Color(0XFFCC9900),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(16.dp)
                                ) {
                                    Row {
                                        Column(
                                            modifier = Modifier
                                                .width(180.dp)
                                                .height(110.dp)
                                        ) {
                                            Text(
                                                text = card.fullName,
                                                color = Color(0XFFCC9900),
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                            Spacer(modifier = Modifier.height(10.dp))

                                            Text(
                                                text = card.number,
                                                color = Color(0XFFCC9900),
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Row {

                                            Spacer(modifier = Modifier.weight(1f))
                                            Column(
                                                modifier = Modifier
                                                    .width(160.dp)
                                                    .height(100.dp)
                                            ) {
                                                var expanded by remember { mutableStateOf(false) }
                                                Box(
                                                    modifier = Modifier.align(Alignment.End)
                                                ) {
                                                    IconButton(onClick = { expanded = true }) {
                                                        Icon(
                                                            imageVector = Icons.Default.MoreVert,
                                                            contentDescription = "Menu",
                                                            tint = Color(0XFFCC9900)
                                                        )
                                                    }

                                                    DropdownMenu(
                                                        expanded = expanded,
                                                        onDismissRequest = { expanded = false }
                                                    ) {
                                                        DropdownMenuItem(
                                                            text = { Text(stringResource(id = R.string.delete_card)) },
                                                            onClick = {
                                                                expanded = false
//                                                    viewModel.removeCard(card)
                                                            }
                                                        )
                                                    }
                                                }

                                                Text(
                                                    text = card.expirationDate,
                                                    color = Color(0XFFCC9900),
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.End,
                                                    modifier = Modifier
                                                        .align(Alignment.End)
                                                )
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))


                }
            } else {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                // Mostramos las tarjetas dinamicamente
                if (uiState.cards != null) {
                    uiState.cards.forEach { card ->
                            Box(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(100.dp)
                                    .background(
                                        color = Color(0XFFCC9900),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(16.dp)
                            ) {
                                Row {
                                    Column(
                                        modifier = Modifier
                                            .width(160.dp)
                                            .height(110.dp)
                                    ) {
                                        Text(
                                            text = card.fullName,
                                            color = Color(0XFFCC9900),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                            text = card.number,
                                            color = Color(0XFFCC9900),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .width(160.dp)
                                            .height(100.dp)
                                    ) {
                                        var expanded by remember { mutableStateOf(false) }
                                        Box(
                                            modifier = Modifier.align(Alignment.End)
                                        ) {
                                            IconButton(onClick = { expanded = true }) {
                                                Icon(
                                                    imageVector = Icons.Default.MoreVert,
                                                    contentDescription = "Menu",
                                                    tint = Color(0XFFCC9900),
                                                )
                                            }

                                            DropdownMenu(
                                                expanded = expanded,
                                                onDismissRequest = { expanded = false }
                                            ) {
                                                DropdownMenuItem(
                                                    text = { Text(stringResource(id = R.string.delete_card)) },
                                                    onClick = {
                                                        expanded = false
            //                                                    viewModel.removeCard(card)
                                                    }
                                                )
                                            }
                                        }

                                        Text(
                                            text = card.expirationDate,
                                            color = Color(0XFFCC9900),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.End,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    } else {
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        goToCreateCard()
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
                        text = stringResource(id = R.string.add_card),
                        fontSize = 19.sp
                    )
                }
            }
        }
    }
}



//@PreviewScreenSizes
//@Composable
//fun CardsScreenPreview(){
//    CardsScreen(
//        goToCreateCard = {},
//        viewModel = CardsViewModel()
//    )
//}