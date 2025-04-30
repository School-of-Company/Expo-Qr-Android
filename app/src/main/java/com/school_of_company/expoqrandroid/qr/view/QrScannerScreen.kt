@file:OptIn(ExperimentalPermissionsApi::class)

package com.school_of_company.expoqrandroid.qr.view

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.school_of_company.expoqrandroid.R
import com.school_of_company.expoqrandroid.qr.view.component.QrcodeScanView
import com.school_of_company.expoqrandroid.ui.theme.ExpoQrAndroidTheme
import com.school_of_company.expoqrandroid.ui.theme.Typography
import com.school_of_company.expoqrandroid.util.ThemeDevicePreviews
import org.json.JSONObject

@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
@Composable
fun QrScannerScreen(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner,
    navController: NavController
) {

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    ExpoQrAndroidTheme {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 20.dp),
                        text = "QR코드 찍기",
                        style = Typography.bodyLarge,
                        color = Color.Black,
                    )

                    QrcodeScanView(
                        onQrcodeScan = { qrContent ->
                            try {
                                val json = JSONObject(qrContent)
                                val participantId = json.getString("participantId")
                                val phoneNumber = json.getString("phoneNumber")

                                val url = "https://startup-expo.kr/application/$participantId?formType=survey&userType=STANDARD&applicationType=register&phoneNumber=$phoneNumber"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)

                            } catch (e: Exception) {
                                Log.e("QrScanner", "QR 파싱 실패", e)
                            }
                        },
                        lifecycleOwner = lifecycleOwner,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, end = 16.dp, bottom = 83.dp)
                            .clip(RoundedCornerShape(6.dp))
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.qr_guide),
                    contentDescription = stringResource(id = R.string.qr_guide),
                    modifier = modifier
                )
            }
        }
    }
}

@ThemeDevicePreviews
@Composable
private fun QrScannerPreview() {
    val lifecycleOwner = LocalLifecycleOwner.current
    QrScannerScreen(
        lifecycleOwner = lifecycleOwner,
        modifier = Modifier,
        navController = rememberNavController()
    )
}
