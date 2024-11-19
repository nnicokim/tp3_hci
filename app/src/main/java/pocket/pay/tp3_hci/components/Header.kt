package pocket.pay.tp3_hci.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pocket.pay.tp3_hci.screens.LoginScreen
import pocket.pay.tp3_hci.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    modifier: Modifier = Modifier,
    titleColor: Color = Color.Black,
    highlightColor: Color = Color(0xFF6200EE) // Define Purple color
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp), // Add some padding
                    verticalAlignment = Alignment.CenterVertically // Align text vertically
                ) {
                    Text(
                        text = "Pocket ",
                        color = titleColor,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Pay",
                        color = Color(0xFF7382E5),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        modifier = modifier.height(45.dp)
    )
}

@Preview
@Composable
fun HeaderPreview() {
    Header()
}
