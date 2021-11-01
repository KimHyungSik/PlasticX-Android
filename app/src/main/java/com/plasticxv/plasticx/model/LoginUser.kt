package com.plasticxv.plasticx.model

import com.google.gson.annotations.SerializedName

data class LoginUser(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("fcm_token") var fcm_token: String
)