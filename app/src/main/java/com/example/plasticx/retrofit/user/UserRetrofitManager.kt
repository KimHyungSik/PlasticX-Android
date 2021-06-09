package com.example.plasticx.retrofit.user

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.plasticx.MyApplication
import com.example.plasticx.firebase.MyFirebaseMessagingService
import com.example.plasticx.model.RegisterUser
import com.example.plasticx.utils.RESPONSE_STATUIS
import com.example.plasticx.utils.Utility.BASE_URL
import com.google.gson.Gson
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class UserRetrofitManager {
    companion object {
        val instance = UserRetrofitManager()
        val TAG = "UserRetrofitManager - 로그"
    }

    fun userRegister(
        name: String,
        email: String,
        password: String,
        completion: (RESPONSE_STATUIS, String?) -> Unit
    ) {
        var firebaseToken: String = ""
        MyFirebaseMessagingService().getToken {
            firebaseToken = it
        }

        val registerUser = RegisterUser(
            name,
            email,
            password
        )

        UserRetrofitClient.getClient(BASE_URL)?.create(InUserRetrofit::class.java)
            ?.userRegister(registerUser)
            ?.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                response.body()?.let {
                    val body = it.asJsonObject
                    Log.d(TAG, "onResponse: $body")
                    if (body.get("RESULT").asString == "200") {
                        completion(RESPONSE_STATUIS.OK, body.get("user_id").asString)
                    } else {
                        completion(RESPONSE_STATUIS.ERROR, null)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t}")
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(
                        MyApplication.instance,
                        "서버와 연결에 실패 하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    completion(RESPONSE_STATUIS.SERVER_ERROR, null)
                }
            }

        })

    }
}