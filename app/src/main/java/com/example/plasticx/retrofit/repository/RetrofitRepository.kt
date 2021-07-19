package com.example.plasticx.retrofit.repository

import com.example.plasticx.model.BorrowTumbler
import com.example.plasticx.retrofit.tumbler.InTumblerRetrofit
import com.example.plasticx.retrofit.user.InUserRetrofit
import com.example.plasticx.retrofit.user.UserRetrofitClient
import com.example.plasticx.retrofit.user.UserRetrofitManager
import com.example.plasticx.user.UserManagerObject
import com.example.plasticx.utils.Utility
import com.example.plasticx.utils.Utility.BASE_URL
import com.google.gson.JsonElement
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository {

    private val retrofitBase: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    // 로그 찍어 주는 Retrofit
    private val interceptorRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(UserRetrofitClient.getOkHtpCLient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    // 유저 Retrofit 매니저 전체 반환
    fun userRepository(): UserRetrofitManager {
        return UserRetrofitManager.instance
    }

    // 유저 정보 확인
    fun getUserRxInfo(userId: String): Flowable<JsonElement> =
        retrofitBase
            .create(InUserRetrofit::class.java)
            .userInfo(userId)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.asJsonObject.get("RESULT").asString == "200" } // 유저 정보 확인 성공

    // 유저가 빌린 텀블러 리스트 확인
    fun getUserTumblerList(userId: String) : Observable<JsonElement> =
        retrofitBase
            .create(InUserRetrofit::class.java)
            .userTumblerList(userId)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())


    fun borrowTumblerRx(tumblerId: String): Flowable<JsonElement> =
        retrofitBase
            .create(InTumblerRetrofit::class.java)
            .borrowTumbler(tumblerId, BorrowTumbler(UserManagerObject.userId))
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

}