package com.example.plasticx.retrofit.user

import com.example.plasticx.utils.Utility.BASE_URL
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InUserRetrofit {

    @FormUrlEncoded
    @POST("api/user/register")
    fun userRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("Token") Token: String
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("api/user/login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("Token") Token: String
    )

}