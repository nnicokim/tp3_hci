package pocket.pay.tp3_hci.components

import android.widget.ImageButton
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.ui.theme.Purple


@Composable
fun RecentPayments(){
    var expanded by remember{ mutableStateOf(false) }
    Card(
        onClick = {expanded = !expanded},
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .background(color = Purple)
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row() {
                Text(
                    text = stringResource(R.string.recent_payments),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                IconButton(
                    onClick = {expanded = !expanded}
                ) {
                    Icon(
                        imageVector = if(expanded) Icons.Default.KeyboardArrowRight else Icons.Default.KeyboardArrowDown,
                        contentDescription = if(expanded) stringResource(R.string.colapse) else stringResource(R.string.expand)
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecentPaymentsPreview(){
    RecentPayments()
}
