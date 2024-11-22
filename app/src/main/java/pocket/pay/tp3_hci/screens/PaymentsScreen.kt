package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.components.RecentPayments
import pocket.pay.tp3_hci.ui.theme.Purple


@Composable
fun PaymentsScreen(
    goToNewPayment: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(id = R.string.payments),
                modifier = Modifier.padding(20.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {

                },
                modifier = Modifier.wrapContentWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = stringResource(id = R.string.new_payment),
                    fontSize = 15.sp)
            }


            }

        Text(
            text = stringResource(id = R.string.recent_payments),
            modifier = Modifier.padding(15.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )
        }



    Spacer(modifier = Modifier.height(30.dp))
    }


@Preview(showBackground = true)
@Composable
fun PaymentsScreenPreview(){
    PaymentsScreen {}
}