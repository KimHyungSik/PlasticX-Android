package com.plasticxv.plasticx.utils

object Utility{
    const val CHANNEL_ID = "PlasticX_Channel"
    const val BASE_URL = "http://13.59.10.162/"
    const val USER_ID_KEY = "PLASTICX"
    const val DATABASE_NAME = "PLASTICX-APP-DATABASE"
}

// http통신 결과
enum class RESPONSE_STATE{
    OK,
    FAIL,
    NOTTHING,
    ERROR,
    SERVER_ERROR
}

// 현재 로그인 상태
enum class LOGIN_STATE{
    NO,
    KAKAO,
    LOCAL
}