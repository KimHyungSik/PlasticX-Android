package com.example.plasticx.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.plasticx.base.BaseLoginActivity
import com.example.plasticx.databinding.ActivityLoginBinding
import com.example.plasticx.databinding.ActivityMainBinding
import com.example.plasticx.main.MainActivity
import com.example.plasticx.registration.RegistrationActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginActivity : BaseLoginActivity<ActivityLoginBinding>() {

    private val TAG = "LoginActivity - 로그"

    override val bindingInflater: (LayoutInflater) -> ActivityLoginBinding
            = ActivityLoginBinding::inflate

    override fun setup() {
        setupViews()
    }

    private fun setupViews(){
        binding.singupBtn.setOnClickListener {
            moveIntentAllClear(RegistrationActivity::class.java)
        }

        binding.loginBtn.setOnClickListener {
            if(binding.loginEmailText.text?.length!! == 0){
                binding.logindEmailLayout.error = "이메일을 입력해주세요."
            }else{
                binding.logindEmailLayout.error = ""
            }
            if(binding.loginPasswordText.text?.length!! == 0){
                binding.loginPasswordLayout.error = "비밀번호를 입력해주세요."
            }else{
                binding.logindEmailLayout.error = ""
            }
        }

        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
                moveIntentAllClear(MainActivity::class.java)
            }
        }

        binding.kakaoOauthBtn.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }


}