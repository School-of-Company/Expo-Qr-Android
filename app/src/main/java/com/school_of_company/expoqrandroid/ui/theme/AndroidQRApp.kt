import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.navigation
import com.school_of_company.expoqrandroid.qr.view.QrScannerScreen

@Composable
fun ExpoQrApp() {
    val navController = rememberNavController()
    val lifecycleOwner = LocalLifecycleOwner.current

    NavHost(
        navController = navController,
        startDestination = "app"
    ) {
        navigation(
            route = "app",
            startDestination = "qrScreen"
        ) {
            composable("qrScreen") {
                QrScannerScreen(
                    navController = navController,
                    lifecycleOwner = lifecycleOwner
                )
            }
        }
    }
}
