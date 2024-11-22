package pocket.pay.tp3_hci.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.R

@Composable
fun AliasCBUCard(){
    Card(

    ) {
        Text(
            text = stringResource(id = R.string.your_aliascbu),
            fontSize = 30.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = "Alias",
            fontSize = 45.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )



        OutlinedCard(
            onClick = {},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ){
            Column(
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = "SER.PAPA.AUTO",
                    fontSize = 25.sp,
                )
            }


        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "CBU",
            fontSize = 45.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(10.dp)
        )


        OutlinedCard(
            onClick = {},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ){
            Column(
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = "2850590940090418135201",
                    fontSize = 25.sp,
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun AliasCBUCardPreview(){
    AliasCBUCard()
}