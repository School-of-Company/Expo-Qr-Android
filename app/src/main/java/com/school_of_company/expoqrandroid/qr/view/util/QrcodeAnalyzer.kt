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

    private var hasProcessed = false

    private val scanner = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    )

    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (hasProcessed) {
            imageProxy.close()
            return
        }

        val mediaImage = imageProxy.image ?: run {
            imageProxy.close()
            return
        }

        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                val firstQrCode = barcodes.firstOrNull()?.displayValue
                if (firstQrCode != null) {
                    Log.d("process", firstQrCode)
                    hasProcessed = true
                    qrcodeData(firstQrCode)
                }
            }
            .addOnFailureListener {
                Log.e("QrcodeScanner", "QR code scanning failed", it)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}