package com.example.plasticx.retrofit.user

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

    @FormUrlEncoded
    @POST("api/user/login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("Token") Token: String
    )

}