package com.school_of_company.expoqrandroid.qr.view.util

import android.util.Size
import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
/**
 * QR 코드 분석을 위한 ImageAnalysis 객체를 생성하는 함수
 **/
internal fun buildImageAnalysis(
    context: android.content.Context,
    onQrcodeScanned: (String?) -> Unit
): ImageAnalysis {
    return ImageAnalysis.Builder()
        .setTargetResolution(Size(1920, 1080))
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build().apply {
            setAnalyzer(ContextCompat.getMainExecutor(context), QrcodeAnalyzer(onQrcodeScanned))
        }
}
