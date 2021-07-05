package com.example.plasticx.qr

import android.util.Log
import com.example.plasticx.retrofit.repository.RetrofitRepository
import javax.inject.Inject

class QrViewModel @Inject constructor(val retrofitRepository: RetrofitRepository) {

    val TAG = "QrViewModel - 로그"

    fun borrowTumbler(tumblerId: String, voidFun: () -> Unit) {
        retrofitRepository.borrowTumblerRx(tumblerId)
            .map { it.asJsonObject }
            .subscribe(
                {
                    when (it.get("RESULT").asString) {
                        "200" -> {
                            Log.d(TAG, "borrowTumbler: 텀블러 대여 성공")
                        }
                        "300" -> {
                            Log.d(TAG, "borrowTumbler: 보증금 부족")
                        }
                        "301" -> {
                            Log.d(TAG, "borrowTumbler: 텁블러 대여 중")
                        }
                        else -> {
                            Log.d(TAG, "borrowTumbler: ErrorCOde ${it.get("RESULT").asString}")
                        }
                    }
                },
                {
                    Log.d(TAG, "borrowTumbler: Error ${it.message}")
                },
                {
                    Log.d(TAG, "borrowTumbler: Complete")
                    voidFun()
                }
            ).isDisposed
    }
}