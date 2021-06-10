package com.example.plasticx.model

import com.google.gson.annotations.SerializedName

data class RegisterUser(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("Token") var firebaseToken: String
)
