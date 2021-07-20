package com.example.plasticx.retrofit.user

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.plasticx.MyApplication
import com.example.plasticx.firebase.MyFirebaseMessagingService
import com.example.plasticx.model.LoginUser
import com.example.plasticx.model.RegisterUser
import com.example.plasticx.utils.RESPONSE_STATE
import com.example.plasticx.utils.Utility.BASE_URL
import com.google.gson.JsonElement
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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
        completion: (RESPONSE_STATE, String?) -> Unit
    ) {
        val registerUser = RegisterUser(
            name,
            email,
            password,
            ""
        )

        Observable.just(registerUser)
            .subscribeOn(Schedulers.io())
            .subscribe {
                MyFirebaseMessagingService().getToken { token ->
                    it.fcm_token = token
                    UserRetrofitClient.getClient(BASE_URL)?.create(InUserRetrofit::class.java)
                        ?.userRegister(it)
                        ?.enqueue(object : retrofit2.Callback<JsonElement> {
                            override fun onResponse(
                                call: Call<JsonElement>,
                                response: Response<JsonElement>
                            ) {
                                response.body()?.let {
                                    val body = it.asJsonObject
                                    if (body.get("RESULT").asString == "200") {
                                        completion(RESPONSE_STATE.OK, body.get("user_id").asString)
                                    } else {
                                        completion(RESPONSE_STATE.FAIL, body.get("RESULT").asString)
                                    }
                                }
                            }

                            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                                toast("서버와 연결에 실패 하였습니다.")
                                completion(RESPONSE_STATE.SERVER_ERROR, null)
                            }

                        })
                }
            }.isDisposed
    }

    fun userLogin(
        email: String,
        password: String,
        completion: (RESPONSE_STATE, String?) -> Unit
    ) {

        val loginUser = LoginUser(email, password, "")

        Observable.just(loginUser)
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.d(TAG, "userLogin: $loginUser")
                MyFirebaseMessagingService().getToken { token ->
                    it.fcm_token = token
                    UserRetrofitClient.getClient(BASE_URL)?.create(InUserRetrofit::class.java)
                        ?.userLogin(it)
                        ?.enqueue(object : retrofit2.Callback<JsonElement> {
                            override fun onResponse(
                                call: Call<JsonElement>,
                                response: Response<JsonElement>
                            ) {
                                response.body()?.let {
                                    val body = it.asJsonObject
                                    Log.d(TAG, "onResponse: $body")
                                    if (body.get("RESULT").asString == "200") {
                                        completion(RESPONSE_STATE.OK, body.get("user_id").asString)
                                    } else {
                                        completion(
                                            RESPONSE_STATE.ERROR,
                                            body.get("RESULT").asString
                                        )
                                    }
                                }
                            }

                            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                                toast("서버와 연결에 실패 하였습니다.")
                                completion(RESPONSE_STATE.SERVER_ERROR, null)
                            }
                        })
                }
            }.isDisposed
    }


    private fun toast(massage: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(
                MyApplication.instance,
                massage,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}