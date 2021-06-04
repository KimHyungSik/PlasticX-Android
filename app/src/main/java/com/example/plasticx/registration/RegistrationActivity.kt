package com.example.plasticx.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.plasticx.base.BaseLoginActivity
import com.example.plasticx.databinding.ActivityLoginBinding
import com.example.plasticx.databinding.ActivityRegistrationBinding

class RegistrationActivity : BaseLoginActivity<ActivityRegistrationBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityRegistrationBinding
            = ActivityRegistrationBinding::inflate

    override fun setup() {
        binding.registerBtn.setOnClickListener {
            if(binding.registrationEmail.text?.length == 0){
                binding.registrationEmailLayout.error = "빈워 둘 수 없습니다."
            }else{
                binding.registrationEmailLayout.error = ""
            }
            if(binding.registrationUserName.text?.length == 0){
                binding.registrationUserNameLayout.error = "빈워 둘 수 없습니다."
            }else{
                binding.registrationUserNameLayout.error = ""
            }
            if(binding.registrationPassword.text?.length == 0){
                binding.registrationPasswordLayout.error = "빈워 둘 수 없습니다."
            }else{
                binding.registrationPasswordLayout.error = ""
            }
            if(binding.registrationPasswordConfirm.text?.length == 0){
                binding.registrationPasswordConfirmLayout.error = "빈워 둘 수 없습니다."
            }else{
                binding.registrationPasswordConfirmLayout.error = ""
            }

            if(binding.registrationPassword.text != binding.registrationPasswordConfirm){
                binding.registrationPasswordConfirmLayout.error = "비밀번호를 확인해 주세요."
            }else{
                binding.registrationPasswordConfirmLayout.error = ""
            }
        }
    }

}