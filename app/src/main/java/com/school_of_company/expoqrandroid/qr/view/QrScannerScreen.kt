package com.school_of_company.expoqrandroid.qr.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.school_of_company.expoqrandroid.R
import com.school_of_company.expoqrandroid.qr.view.component.QrcodeScanView
import com.school_of_company.expoqrandroid.ui.theme.ExpoQrAndroidTheme
import com.school_of_company.expoqrandroid.ui.theme.Typography
import com.school_of_company.expoqrandroid.util.ThemeDevicePreviews


@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
@Composable
private fun QrScannerScreen(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner,
    onQrcodeScan: (String) -> Unit,
) {
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
                        onQrcodeScan = onQrcodeScan,
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
        onQrcodeScan = {},
        modifier = Modifier
    )
}