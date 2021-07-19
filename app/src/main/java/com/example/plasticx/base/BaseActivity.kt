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
abstract class BaseActivity<VB : ViewBinding>() : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB
    val requestActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult() // ◀ StartActivityForResult 처리를 담당
    ) { activityResult -> resultActivity(activityResult)}


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

    // 액티비티 스택을 모두 제거 후 화면 이동
    open fun moveIntentAllClear(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    // 화면 이동
    open fun moveIntent(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    // 리턴 값을 가지는 화면 이동
    open fun moveIntentResult(activity: Class<*>){
        val intent = Intent(this, activity)
        requestActivity.launch(intent)
    }

    // 리턴 값을 가지고 반환된 액티비티 설정
    open fun resultActivity(activityResult: ActivityResult) {

    }

    open fun showLoadingAni(){
        loading.show()
    }
    open fun dismissLoadingAni(){
        loading.dismiss()
    }


}