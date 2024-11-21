package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pocket.pay.tp3_hci.components.RecentPayments


@Composable
fun PaymentsScreen() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize()
        .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        RecentPayments()
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentsScreenPreview(){
    PaymentsScreen()
}