package com.example.plasticx.utils

object Utility{
    const val CHANNEL_ID = "PlasticX_Channel"
    const val BASE_URL = "http://13.59.10.162/"
    const val USER_ID_KEY = "PLASTICX"
}

enum class RESPONSE_STATUIS{
    OK,
    FAIL,
    ERROR,
    SERVER_ERROR
}

enum class LOGIN_STATUIS{
    NO,
    KAKAO,
    LOCAL
}