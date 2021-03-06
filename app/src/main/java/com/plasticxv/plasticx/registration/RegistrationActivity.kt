package com.plasticxv.plasticx.registration

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.plasticxv.plasticx.MyApplication
import com.plasticxv.plasticx.base.BaseLoginActivity
import com.plasticxv.plasticx.dagger.viewmodelFactory.RetrofitFactory
import com.plasticxv.plasticx.databinding.ActivityRegistrationBinding
import com.plasticxv.plasticx.login.LoginActivity
import com.plasticxv.plasticx.utils.RESPONSE_STATE
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
        setUpViewModel()
    }

    private fun setUpViewModel(){
        registrationViewModel._loading.observe(this,{
            if(it){
               showLoadingAni()
            }else{
                dismissLoadingAni()
            }
        })

        registrationViewModel._registerStatu.observe(this, {
            when(it){
                RESPONSE_STATE.OK -> {
                    setResult(RESULT_OK)
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("registerEmail", binding.registrationEmail.text!!.toString())
                    intent.putExtra("registerPassword", binding.registrationPassword.text!!.toString())
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                RESPONSE_STATE.FAIL -> {
                    binding.registrationEmailLayout.error = "이미 존재하는 이메일 입니다."
                }
                else -> {}
            }
        })
    }

}