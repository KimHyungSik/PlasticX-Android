package com.plasticxv.plasticx.retrofit.tumbler

import com.plasticxv.plasticx.model.BorrowTumbler
import com.google.gson.JsonElement
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface InTumblerRetrofit {

    @Headers("Content-Type: application/json")
    @POST("/api/tumbler/{tumbler_id}")
    fun borrowTumbler(
        @Path("tumbler_id") tumblerId: String,
        @Body body: BorrowTumbler
    ): Flowable<JsonElement>

}