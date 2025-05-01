package com.school_of_company.expoqrandroid.qr.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 68.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.expoicon),
                        contentDescription = "EXPO 로고",
                        modifier = Modifier
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "QR코드 찍기",
                        style = Typography.bodyLarge,
                        color = Color.Black,
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, bottom = 148.dp)
                ) {

                    QrcodeScanView(
                        onQrcodeScan = onQrcodeScan,
                        lifecycleOwner = lifecycleOwner,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(6.dp))
                    )

                    Image(
                        painter = painterResource(id = R.drawable.qr_guide),
                        contentDescription = stringResource(id = R.string.qr_guide),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 114.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.exclamationmark),
                    contentDescription = stringResource(id = R.string.qr_guide),
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "QR 가이드라인에 맞춰 카메라를 비춰주세요.",
                    style = Typography.bodyMedium,
                    color = Color.Gray,
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