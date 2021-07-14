package com.example.plasticx.main.listfragment

import android.util.Log
import com.example.plasticx.dagger.annotation.MainActivityScope
import com.example.plasticx.model.TumblerItem
import com.example.plasticx.retrofit.repository.RetrofitRepository
import com.example.plasticx.user.UserManagerObject
import com.google.gson.JsonObject
import io.reactivex.Observable
import javax.inject.Inject

@MainActivityScope
class TumblerViewModel @Inject constructor(val retrofitRepository: RetrofitRepository) {

    val TAG = "TumblerViewModel - 로그"

    private var tumblerList = ArrayList<TumblerItem>()
    fun getTumblerList(): Observable<ArrayList<TumblerItem>> = retrofitRepository
        .getUserTumblerList(UserManagerObject.userId)
        .map { it.asJsonObject }
        .map {
            for (n in it.get("tumbler_id").asJsonArray) {
                val tumblerItem = TumblerItem(
                    "",
                    "텀블러",
                    "기간 : 00-00"
                )
                tumblerList.add(tumblerItem)
            }
            tumblerList
        }

}