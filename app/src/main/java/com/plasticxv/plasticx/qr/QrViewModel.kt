package com.plasticxv.plasticx.qr

import android.util.Log
import android.widget.Toast
import com.plasticxv.plasticx.MyApplication
import com.plasticxv.plasticx.retrofit.repository.RetrofitRepository
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
                            toast("대여성공")
                            voidFun()
                        }
                        "300" -> {
                            toast("보증금이 부족 합니다.")
                        }
                        "301" -> {
                            toast("텀블러가 대여 중 입니다.")
                        }
                        else -> {
                            toast("텀블러가 대여에 실패하였습니다.")
                            Log.d(TAG, "borrowTumbler: ErrorCode ${it.get("RESULT").asString}")
                        }
                    }
                },
                {
                    Log.d(TAG, "borrowTumbler: Error ${it.message}")
                },
                {
                    Log.d(TAG, "borrowTumbler: Complete")
                }
            ).isDisposed
    }

    fun toast(message: String){
        Toast.makeText(MyApplication.instance, message, Toast.LENGTH_SHORT).show()
    }
}