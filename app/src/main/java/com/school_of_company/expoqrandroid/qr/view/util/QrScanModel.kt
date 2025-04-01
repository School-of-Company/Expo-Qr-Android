package com.school_of_company.expoqrandroid.qr.view.util

import org.json.JSONObject

/**
 * QR 코드 스캔 데이터를 담는 데이터 클래스
 *
 * @property participantId 참가자 ID
 * @property phoneNumber 참가자의 전화번호
 */
data class QrScanModel(
    val participantId: Long,
    val phoneNumber: String
)

/**
 * JSON 문자열을 QrScanModel 객체로 변환하는 확장 함수
 *
 * @receiver JSON 형식의 문자열
 * @return 변환된 QrScanModel 객체
 */
internal fun String.satisfactionQrScanModel(): QrScanModel {
    val jsonObject = JSONObject(this) // JSON 문자열을 JSONObject로 변환
    val participantId = jsonObject.optLong("participantId") // participantId 추출
    val phoneNumber = jsonObject.optString("phoneNumber") // phoneNumber 추출

    return QrScanModel(participantId, phoneNumber) // QrScanModel 객체 반환
}
