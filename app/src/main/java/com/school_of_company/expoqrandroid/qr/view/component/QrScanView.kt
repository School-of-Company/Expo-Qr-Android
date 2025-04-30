package com.school_of_company.expoqrandroid.qr.view.component

import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.school_of_company.expoqrandroid.qr.view.util.setupQrScanCamera

/**
 * QR 코드 스캔을 위한 Composable 뷰.
 * CameraX의 PreviewView를 사용하여 QR 코드 스캔 기능을 제공합니다.
 */
@Composable
internal fun QrcodeScanView(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner,
    onQrcodeScan: (String) -> Unit,
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            // CameraX의 프리뷰를 표시할 PreviewView 생성
            PreviewView(context).apply {
                // 뷰가 완전히 초기화된 후 카메라를 설정하기 위해 post 블록 사용
                post {
                    setupQrScanCamera(
                        previewView = this, // 현재 PreviewView를 전달
                        lifecycleOwner = lifecycleOwner,
                        onQrcodeScanned = onQrcodeScan // QR 코드 스캔 시 실행할 콜백 함수 전달
                    )
                }
            }
        },
    )
}