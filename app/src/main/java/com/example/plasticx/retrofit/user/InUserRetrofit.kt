package com.example.plasticx.retrofit.user

import com.example.plasticx.utils.Utility.BASE_URL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InUserRetrofit {

    @POST(BASE_URL+"api/user/register")
    fun userRegister(
        @Body name: String,
        @Body email: String,
        @Body password: String,
        @Body Token: String
    ): Call<JsonElement>

    @POST(BASE_URL + "api/user/login")
    fun userLogin(
        @Body email: String,
        @Body password: String
    )

}