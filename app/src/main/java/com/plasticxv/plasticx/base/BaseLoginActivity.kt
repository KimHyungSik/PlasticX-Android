package com.plasticxv.plasticx.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.plasticxv.plasticx.loading.LottieLoadingDialog

// 로그인이전 사용하는 베이스 액티비티
abstract class BaseLoginActivity<VB : ViewBinding>() : BaseSetupActivity() {
    open var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB


    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        loading = LottieLoadingDialog(this)
        setup()
    }

    abstract fun setup()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

