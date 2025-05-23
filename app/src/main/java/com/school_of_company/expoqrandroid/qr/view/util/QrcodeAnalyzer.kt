package com.school_of_company.expoqrandroid.qr.view.util

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

internal class QrcodeAnalyzer(
    private val qrcodeData: (String) -> Unit
) : ImageAnalysis.Analyzer {

    // QR 코드 스캐너 클라이언트 초기화
    private val scanner = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            // 스캔할 바코드 포맷: QR 코드로 설정
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    )

    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage == null) {
            Log.e("QrcodeAnalyzer", "mediaImage is null")
            imageProxy.close()
            return
        }

        val rotation = imageProxy.imageInfo.rotationDegrees
        val image = InputImage.fromMediaImage(mediaImage, rotation)

        // QR 코드 스캔 시작
        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                val firstQrCode = barcodes.firstOrNull()?.displayValue
                if (firstQrCode != null) {
                    Log.d("process",firstQrCode.toString())

                    // 콜백 함수로 QR 코드 데이터 전달
                    qrcodeData(firstQrCode)
                }
            }
            .addOnFailureListener {
                // QR 코드 스캔 실패 시 로그 출력
                Log.d("QrcodeScanner", "QR code scanning failed", it)
            }
            .addOnCompleteListener {
                // 스캔 작업이 완료되면 ImageProxy를 닫아 자원 해제
                imageProxy.close()
            }
    }
}