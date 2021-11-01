package com.plasticxv.plasticx.main.listfragment

import android.util.Log
import com.plasticxv.plasticx.dagger.annotation.MainActivityScope
import com.plasticxv.plasticx.model.TumblerItem
import com.plasticxv.plasticx.retrofit.repository.RetrofitRepository
import com.plasticxv.plasticx.user.UserManagerObject
import io.reactivex.Observable
import javax.inject.Inject
import kotlin.collections.ArrayList

@Suppress("DEPRECATION", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@MainActivityScope
class TumblerViewModel @Inject constructor(val retrofitRepository: RetrofitRepository) {

    val TAG = "TumblerViewModel - 로그"

    var tumblerList = ArrayList<TumblerItem>()
    
    fun getTumblerList(): Observable<ArrayList<TumblerItem>> = retrofitRepository
        .getUserTumblerList(UserManagerObject.userId)
        .map { it.asJsonObject }
        .map {
            var _tumblerList = ArrayList<TumblerItem>()
            for (n in it.get("tumblers").asJsonArray) {
                val jsonObject = n.asJsonObject

                val tumblerItem = TumblerItem(
                    "",
                    jsonObject.get("model").asString,
                    jsonObject.get("borrowed_date").asString,
                    jsonObject.get("usable_period_date").asString,
                    jsonObject.get("shop").asString,
                    true
                )

                _tumblerList.add(tumblerItem)
            }
            _tumblerList
        }

    fun getTumblerHistoryList(): Observable<ArrayList<TumblerItem>> =
        retrofitRepository
            .getUserTumblerHistoryList(UserManagerObject.userId)
            .map { it.asJsonObject }
            .map {
                val _tumblerList = ArrayList<TumblerItem>()
                for (n in it.get("tumblers_returned").asJsonArray) {
                    val jsonObject = n.asJsonObject

                    Log.d(TAG, "getTumblerHistoryList: $jsonObject")

                    val tumblerItem = TumblerItem(
                        "",
                        jsonObject.get("model").asString,
                        "",
                        jsonObject.get("returned_date").asString,
                        jsonObject.get("shop").asString,
                        false
                    )

                    _tumblerList.add(tumblerItem)
                }
                _tumblerList
            }

}