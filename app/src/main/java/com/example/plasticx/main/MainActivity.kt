package com.example.plasticx.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.plasticx.R
import com.example.plasticx.base.BaseActivity
import com.example.plasticx.databinding.ActivityLoginBinding
import com.example.plasticx.databinding.ActivityMainBinding
import com.example.plasticx.login.LoginActivity
import com.kakao.sdk.user.UserApiClient

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val TAG = "MainActivity - 로그"

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
            = ActivityMainBinding::inflate

    override fun setup() {
        setupViews()
    }

    private fun setupViews(){
        binding.logoutTest.setOnClickListener{
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                }
                else {
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }
    }
}