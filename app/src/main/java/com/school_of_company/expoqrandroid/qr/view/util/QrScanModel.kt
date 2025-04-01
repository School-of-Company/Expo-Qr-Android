package com.school_of_company.expoqrandroid.qr.view.util

import org.json.JSONObject

data class QrScanModel(
    val participantId: Long,
    val phoneNumber: String
)

internal fun String.satisfactionQrScanModel(): QrScanModel {
    val jsonObject = JSONObject(this)
    val participantId = jsonObject.optLong("participantId")
    val phoneNumber = jsonObject.optString("phoneNumber")

    return QrScanModel(participantId, phoneNumber)
}
