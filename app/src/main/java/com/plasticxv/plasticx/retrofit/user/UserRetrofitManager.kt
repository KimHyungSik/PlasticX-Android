package com.plasticxv.plasticx.retrofit.user

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.plasticxv.plasticx.MyApplication
import com.plasticxv.plasticx.firebase.MyFirebaseMessagingService
import com.plasticxv.plasticx.model.KakaoLoginUser
import com.plasticxv.plasticx.model.LoginUser
import com.plasticxv.plasticx.model.RegisterUser
import com.plasticxv.plasticx.utils.LOGIN_STATE
import com.plasticxv.plasticx.utils.RESPONSE_STATE
import com.plasticxv.plasticx.utils.Utility.BASE_URL
import com.google.gson.JsonElement
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
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


    // 두 종류의 로그인을 처리하기 위해 로그인 상태를 받는다
   fun userLogin(
        _id: String?,
        email: String,
        password: String,
        loginState: LOGIN_STATE,
        completion: (RESPONSE_STATE, String?) -> Unit
    ) {

        val loginUser = LoginUser(email, password, "")
        val kakaoUserLogin = KakaoLoginUser(
            _id, email, password, ""
        )

        Log.d(TAG, "userLogin: $kakaoUserLogin")
        
        Observable.just(loginUser)
            .subscribeOn(Schedulers.io())
            .subscribe {
                MyFirebaseMessagingService().getToken { token ->
                    loginUser.fcm_token = token
                    kakaoUserLogin.fcm_token = token
                    val client =
                        UserRetrofitClient.getClient(BASE_URL)?.create(InUserRetrofit::class.java)

                    (if (loginState == LOGIN_STATE.KAKAO) client?.userLogin(kakaoUserLogin)
                    else client?.userLogin(loginUser))
                        ?.enqueue(object : Callback<JsonElement> {
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