package com.example.plasticx.registration

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.plasticx.MyApplication
import com.example.plasticx.base.BaseLoginActivity
import com.example.plasticx.dagger.di.ViewModelFactoryModule
import com.example.plasticx.dagger.viewmodelFactory.RetrofitFactory
import com.example.plasticx.databinding.ActivityRegistrationBinding
import com.example.plasticx.loading.LottieLoadingDialog
import com.example.plasticx.main.MainActivity
import com.example.plasticx.utils.RESPONSE_STATUIS
import javax.inject.Inject

class RegistrationActivity : BaseLoginActivity<ActivityRegistrationBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityRegistrationBinding
            = ActivityRegistrationBinding::inflate

    private lateinit var registrationViewModel: RegistrationViewModel
    private lateinit var loading : LottieLoadingDialog

    @Inject lateinit var retrofitFactory: RetrofitFactory

    override fun setup() {
        (application as MyApplication).appComponent.inject(this)
        loading = LottieLoadingDialog(this)

        registrationViewModel = ViewModelProvider(this, retrofitFactory).get(RegistrationViewModel::class.java)

        binding.registerBtn.setOnClickListener {
            var flag = false
            if(binding.registrationEmail.text?.length == 0){
                binding.registrationEmailLayout.error = "빈워 둘 수 없습니다."
                flag = true
            }else{
                binding.registrationEmailLayout.error = ""
            }
            if(binding.registrationUserName.text?.length == 0){
                binding.registrationUserNameLayout.error = "빈워 둘 수 없습니다."
                flag = true
            }else{
                binding.registrationUserNameLayout.error = ""
            }
            if(binding.registrationPassword.text?.length == 0){
                binding.registrationPasswordLayout.error = "빈워 둘 수 없습니다."
                flag = true
            }else{
                binding.registrationPasswordLayout.error = ""
            }
            if(binding.registrationPasswordConfirm.text?.length == 0){
                binding.registrationPasswordConfirmLayout.error = "빈워 둘 수 없습니다."
                flag = true
            }else{
                binding.registrationPasswordConfirmLayout.error = ""
            }

            if(binding.registrationPassword.text.toString() != binding.registrationPasswordConfirm.text.toString()){
                binding.registrationPasswordConfirmLayout.error = "비밀번호를 확인해 주세요."
                flag = true
            }else{
                binding.registrationPasswordConfirmLayout.error = ""
            }

            if(binding.registrationPassword.text?.length!! < 6){
                binding.registrationPasswordLayout.error = "비밀번호는 6자리 이상 입력해 주세요."
                flag = true
            }else{
                binding.registrationPasswordConfirmLayout.error = ""
            }

            if(!flag) {
                registrationViewModel.userRegister(
                    binding.registrationUserName.text!!.toString(),
                    binding.registrationEmail.text!!.toString(),
                    binding.registrationPassword.text!!.toString()
                )
            }
        }

        // setUp
        viewModel()
    }

    private fun viewModel(){
        registrationViewModel._loading.observe(this,{
            if(it){
               loading.show()
            }else{
               loading.dismiss()
            }
        })

        registrationViewModel._registerStatu.observe(this, {
            when(it){
                RESPONSE_STATUIS.OK -> {moveIntentAllClear(MainActivity::class.java)}
                RESPONSE_STATUIS.FAIL -> {binding.registrationEmailLayout.error = "이미 존재하는 이메일 입니다."}
                else -> {}
            }
        })
    }

}