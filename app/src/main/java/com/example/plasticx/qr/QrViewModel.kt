package com.example.plasticx.qr

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.plasticx.MyApplication
import com.example.plasticx.retrofit.repository.RetrofitRepository
import com.google.gson.JsonElement
import javax.inject.Inject


class QrViewModel @Inject constructor(val retrofitRepository: RetrofitRepository) {

    val TAG = "QrViewModel - 로그"

    fun borrowTumbler(tumblerId: String) {
        retrofitRepository.borrowTumblerRx(tumblerId)
            .map{it -> {
                Log.d(TAG, "borrowTumbler: ${it.asJsonObject.asString}")
            }}
            .subscribe(
                { Log.d(TAG, "borrowTumbler: $it") },
                {
                    Log.d(TAG, "borrowTumbler: Error $it")
                },
                { Log.d(TAG, "borrowTumbler: Complete") }
            ).isDisposed
    }

}