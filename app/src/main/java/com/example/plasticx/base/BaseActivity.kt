package com.example.plasticx.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.UserManager
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.plasticx.loading.LottieLoadingDialog
import com.example.plasticx.login.LoginActivity
import com.example.plasticx.user.UserManagerObject
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

abstract class BaseActivity<VB : ViewBinding>() : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB
    val requestActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult() // ◀ StartActivityForResult 처리를 담당
    ) { activityResult -> resultActivity()}


    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB
    private lateinit var loading : LottieLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        loading = LottieLoadingDialog(this)

        checkLogin()
        setup()
    }

    abstract fun setup()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // 로그인 상태가 아니라면 로그인 페이지로 이동
    private fun checkLogin(){
        if(UserManagerObject.userId.isEmpty()){
            moveIntentAllClear(LoginActivity::class.java)
        }
    }

    open fun moveIntentAllClear(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    open fun moveIntent(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    open fun moveIntentResult(activity: Class<*>){
        val intent = Intent(this, activity)
        requestActivity.launch(intent)
    }

    open fun resultActivity(){

    }

    open fun showLoadingAni(){
        loading.show()
    }
    open fun dismissLoadingAni(){
        loading.dismiss()
    }


}