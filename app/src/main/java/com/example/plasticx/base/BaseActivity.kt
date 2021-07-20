package com.example.plasticx.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.plasticx.loading.LottieLoadingDialog
import com.example.plasticx.login.LoginActivity
import com.example.plasticx.user.UserManagerObject

// 로그인 이후 사용하는 베이스 액티비티
abstract class BaseActivity<VB : ViewBinding>() : BaseSetupActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        loading = LottieLoadingDialog(this)

        checkLogin()
        setup()
    }

    // 최초 액티비티 설정 시 사
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


}