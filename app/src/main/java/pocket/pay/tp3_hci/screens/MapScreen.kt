package pocket.pay.tp3_hci.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Google Maps") })
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
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen()
}

