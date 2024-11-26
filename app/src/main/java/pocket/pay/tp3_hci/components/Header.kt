package pocket.pay.tp3_hci.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pocket.pay.tp3_hci.PreviewScreenSizes
import pocket.pay.tp3_hci.R
import pocket.pay.tp3_hci.ui.theme.Purple

@Composable
fun Header(
    modifier: Modifier = Modifier,
    username: String,
    navController: NavController
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Purple)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

    }
}

//@PreviewScreenSizes
//@Composable
//fun HeaderPreview(){
//    val mockNavController = rememberNavController() // Dummy NavController for preview
//    Header(
//        username = "John Doe",
//        navController = mockNavController
//    )
//}


