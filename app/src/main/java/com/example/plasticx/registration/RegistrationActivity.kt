package com.example.plasticx.registration

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.plasticx.MyApplication
import com.example.plasticx.base.BaseLoginActivity
import com.example.plasticx.dagger.di.ViewModelFactoryModule
import com.example.plasticx.dagger.viewmodelFactory.RetrofitFactory
import com.example.plasticx.databinding.ActivityRegistrationBinding
import javax.inject.Inject

class RegistrationActivity : BaseLoginActivity<ActivityRegistrationBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityRegistrationBinding
            = ActivityRegistrationBinding::inflate

    private lateinit var registrationViewModel: RegistrationViewModel

    @Inject lateinit var retrofitFactory: RetrofitFactory

    override fun setup() {
        (application as MyApplication).appComponent.inject(this)

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

            if(!flag) {
                registrationViewModel.userRegister(
                    binding.registrationUserName.text!!.toString(),
                    binding.registrationEmail.text!!.toString(),
                    binding.registrationPassword.text!!.toString()
                )
            }

        }
    }

}