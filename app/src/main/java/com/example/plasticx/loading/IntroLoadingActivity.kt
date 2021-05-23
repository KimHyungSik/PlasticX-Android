package com.example.plasticx.loading

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.plasticx.databinding.ActivityIntroLoadingBinding
import com.example.plasticx.login.LoginActivity
import com.example.plasticx.main.MainActivity
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroLoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroLoadingBinding
    private val TAG = "IntroLoadingActivity - 로그"
    private val PERMISSION_REQUEST_CODE = 100

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch {
            delay(900)
            permissions()
        }
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

    private fun permissions(){
        if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M){
            if(!checkCAMERAPermission()){
               ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CODE)
            }else{
                kakoTokenAccess()
            }
        }
    }

    private fun checkCAMERAPermission(): Boolean{
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_REQUEST_CODE ->{
                if(grantResults.size > 0){
                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if(cameraAccepted){
                        // 권한 허가 받음
                        kakoTokenAccess()
                        return
                    }
                }else{
                    // 권한 승인 거절
                    finish()
                }
            }
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