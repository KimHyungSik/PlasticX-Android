package com.example.plasticx.retrofit.user

import com.example.plasticx.model.KakaoLoginUser
import com.example.plasticx.model.LoginUser
import com.example.plasticx.model.RegisterUser
import com.google.gson.JsonElement
import io.reactivex.Flowable
import io.reactivex.Observable
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

    @Headers("Content-Type: application/json")
    @POST("api/user/login")
    fun userLogin(
        @Body body: KakaoLoginUser
    ): Call<JsonElement>

    @Headers("Content-Type: application/json")
    @GET("api/user/info/{user_id}")
    fun userInfo(
        @Path("user_id") userId: String
    ): Flowable<JsonElement>

    @Headers("Content-Type: application/json")
    @GET("api/user/list/{user_id}")
    fun userTumblerList(
        @Path("user_id") userId: String
    ): Observable<JsonElement>

    @Headers("Content-Type: application/json")
    @GET("api/user/history/{user_id}")
    fun userTumblerHistoryList(
        @Path("user_id") userId: String
    ): Observable<JsonElement>
}