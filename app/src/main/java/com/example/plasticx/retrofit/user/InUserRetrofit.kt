package com.example.plasticx.retrofit.user

import com.example.plasticx.model.LoginUser
import com.example.plasticx.model.RegisterUser
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface InUserRetrofit {

    @Headers("Content-Type: application/json")
    @POST("api/user/register")
    fun userRegister(
        @Body body: RegisterUser
    ): Call<JsonElement>

    @Headers("Content-Type: application/json")
    @POST("api/user/login")
    fun userLogin(
        @Body body: LoginUser
    ): Call<JsonElement>

}