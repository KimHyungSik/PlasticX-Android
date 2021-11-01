package com.plasticxv.plasticx.model

import com.google.gson.annotations.SerializedName

data class KakaoLoginUser(
    @SerializedName("_id") val _id: String?,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("fcm_token") var fcm_token: String
)
