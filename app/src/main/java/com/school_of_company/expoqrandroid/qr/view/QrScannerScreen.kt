package com.school_of_company.expoqrandroid.qr.view

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.school_of_company.expoqrandroid.R
import com.school_of_company.expoqrandroid.qr.view.component.QrcodeScanView
import com.school_of_company.expoqrandroid.ui.theme.Typography
import org.json.JSONObject

@OptIn(ExperimentalPermissionsApi::class)
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
@Composable
internal fun QrScannerScreen(
    modifier: Modifier = Modifier,
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val context = LocalContext.current
    val lifecycleOwner = context as? LifecycleOwner
        ?: throw IllegalStateException("Context is not a LifecycleOwner")

    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 68.dp),
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                )
            ) {

                Image(
                    painter = painterResource(id = R.drawable.expoicon),
                    contentDescription = "EXPO 로고",
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
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 148.dp
                    )
            ) {

                QrcodeScanView(
                     onQrcodeScan = { qrContent ->
                         try {
                             val json = JSONObject(qrContent)

                             val expoId = json.getString("expoId")
                             val phoneNumber = json.getString("phoneNumber")

                             val url = "https://startup-expo.kr/application/$expoId?formType=survey&userType=STANDARD&applicationType=register&phoneNumber=$phoneNumber"
                             val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                             context.startActivity(intent)

                         } catch (e: Exception) {
                             Log.e("QrScanner", "QR 파싱 실패", e)
                             Toast.makeText(context, "박람회를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                         }
                    },
                    lifecycleOwner = lifecycleOwner,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                )

                Image(
                    painter = painterResource(id = R.drawable.qr_guide),
                    contentDescription = stringResource(id = R.string.qr_guide),
                    modifier = Modifier.align(Alignment.Center).size(400.dp)
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



