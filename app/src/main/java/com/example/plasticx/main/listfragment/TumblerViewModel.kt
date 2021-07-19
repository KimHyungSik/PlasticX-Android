package com.example.plasticx.main.listfragment

import android.annotation.SuppressLint
import android.util.Log
import com.example.plasticx.dagger.annotation.MainActivityScope
import com.example.plasticx.model.TumblerItem
import com.example.plasticx.retrofit.repository.RetrofitRepository
import com.example.plasticx.user.UserManagerObject
import com.google.gson.JsonObject
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@MainActivityScope
class TumblerViewModel @Inject constructor(val retrofitRepository: RetrofitRepository) {

    val TAG = "TumblerViewModel - 로그"
    @SuppressLint("SimpleDateFormat")
    val apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    var tumblerList = ArrayList<TumblerItem>()
    fun getTumblerList(): Observable<ArrayList<TumblerItem>> = retrofitRepository
        .getUserTumblerList(UserManagerObject.userId)
        .map { it.asJsonObject }
        .map {
            for (n in it.get("tumblers").asJsonArray) {
                val jsonObject = n.asJsonObject
                Log.d(TAG, "getTumblerList: $jsonObject")
                val date = apiDateFormat.parse(jsonObject.get("borrowed_date").asString)
                val tumblerItem = TumblerItem(
                    "",
                    jsonObject.get("model").asString,
                    date.month.toString() + '.' + date.day.toString(),
                    date.month.toString() + '.' + (date.day+7).toString()
                )
                tumblerList.add(tumblerItem)
            }
            tumblerList
        }

}