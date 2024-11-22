package pocket.pay.tp3_hci

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "phone",
    group = "screen size",
    device = "spec:width=411dp,height=891dp",
    showBackground = true
)

@Preview(
    name = "tablet",
    group = "screen size",
    device = "spec:width=1280dp,height=800dp,dpi=240",
    showBackground = true
)

annotation class PreviewScreenSizes {
}