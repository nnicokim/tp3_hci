package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.MapView
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pocket.pay.tp3_hci.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(goBackToHome : () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("  Google Maps") },
                navigationIcon = {
                    Button(onClick = { goBackToHome() },
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.go_back_icon),
                            contentDescription = "Go back",
                            tint = Color.White,
                            modifier = Modifier.size(29.dp)
                        )
                    }
                })
        }
    ) { paddingValues ->
        GoogleMapComposable(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun GoogleMapComposable(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    DisposableEffect(Unit) {
        mapView.onCreate(null)
        mapView.onResume()

        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }

    AndroidView(
        // factory = { mapView },
        factory = { context ->
            MapView(context).apply {
                // Configurar MapView
                onCreate(null)
                onResume()
                getMapAsync { googleMap ->
                    googleMap.uiSettings.isZoomControlsEnabled = true
                    googleMap.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(-34.603722, -58.381592),
                            12f
                        )
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(-34.603722, -58.381592))
                            .title("Sucursal Obelisco")

                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(-34.605579, -58.373827))
                            .title("Itba SDF")

                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(-34.605563582295474, -58.37252855300904))
                            .title("Banco Galicia")

                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen(goBackToHome = {})
}


