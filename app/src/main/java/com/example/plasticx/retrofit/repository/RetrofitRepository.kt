package com.example.plasticx.retrofit.repository

import com.example.plasticx.retrofit.user.InUserRetrofit
import com.example.plasticx.retrofit.user.UserRetrofitManager
import com.example.plasticx.utils.Utility
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository {
    fun userRepository(): UserRetrofitManager{
        return UserRetrofitManager.instance
    }

    fun getUserRxInfo() = Retrofit
        .Builder()
        .baseUrl(Utility.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(InUserRetrofit::class.java)
}