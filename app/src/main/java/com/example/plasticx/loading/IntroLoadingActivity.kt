package com.example.plasticx.loading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.plasticx.databinding.ActivityIntroLoadingBinding
import com.example.plasticx.login.LoginActivity
import com.example.plasticx.main.MainActivity
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class IntroLoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroLoadingBinding
    private val TAG = "IntroLoadingActivity - 로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        kakoTokenAccess()
    }

    private fun kakoTokenAccess(){
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                Log.d(TAG, "kakoTokenAccess: $error")
                if (error != null) {
                    //로그인 필요
                    moveIntentAllClear(LoginActivity::class.java)
                }
                else {
                    // 로그인 상태
                    moveIntentAllClear(MainActivity::class.java)
                }
            }
        }else{
            //로그인 필요
            moveIntentAllClear(LoginActivity::class.java)
        }
    }

    private fun moveIntentAllClear(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}