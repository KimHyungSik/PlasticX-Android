package com.example.plasticx.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.plasticx.base.BaseActivity
import com.example.plasticx.databinding.ActivityLoginBinding
import com.example.plasticx.main.MainActivity
import com.example.plasticx.registration.RegistrationActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity - 로그"
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }


    private fun setupViews(){
        binding.singupBtn.setOnClickListener {
            moveIntent(RegistrationActivity::class.java)
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
                binding.loginPasswordLayout.error = ""
            }
        }

        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
                moveIntent(MainActivity::class.java)
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

    private fun moveIntent(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}