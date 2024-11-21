package pocket.pay.tp3_hci.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Cards(
    modifier: Modifier = Modifier
){
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(8.dp,4.dp),
        modifier = modifier
    ) {
    }
}

@Preview
@Composable
fun CardsPreview(){
    Cards()
}