package com.plasticxv.plasticx.base

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.plasticxv.plasticx.loading.LottieLoadingDialog

open class BaseSetupActivity
    : AppCompatActivity()
{
    lateinit var loading : LottieLoadingDialog
    val requestActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult() // ◀ StartActivityForResult 처리를 담당
    ) { activityResult -> resultActivity(activityResult)}

    open fun moveIntent(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    // 리턴 값을 가지는 화면 이동
    open fun moveIntentResult(activity: Class<*>){
        val intent = Intent(this, activity)
        requestActivity.launch(intent)
    }

    open fun moveIntentAllClear(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    open fun showLoadingAni(){
        loading.show()
    }
    open fun dismissLoadingAni(){
        loading.dismiss()
    }

    // 리턴 값을 가지고 반환된 액티비티 설정
    open fun resultActivity(activityResult: ActivityResult) {

    }

}