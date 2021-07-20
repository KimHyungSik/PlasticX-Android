package com.example.plasticx.login

import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.plasticx.MyApplication
import com.example.plasticx.base.BaseLoginActivity
import com.example.plasticx.dagger.viewmodelFactory.RetrofitFactory
import com.example.plasticx.databinding.ActivityLoginBinding
import com.example.plasticx.main.MainActivity
import com.example.plasticx.registration.RegistrationActivity
import com.example.plasticx.user.UserManagerObject
import com.example.plasticx.utils.LOGIN_STATE
import com.example.plasticx.utils.RESPONSE_STATE
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import javax.inject.Inject

class LoginActivity : BaseLoginActivity<ActivityLoginBinding>() {

    private val TAG = "LoginActivity - 로그"
    private var lastTimeBackPressed : Long = 0

    override val bindingInflater: (LayoutInflater) -> ActivityLoginBinding =
        ActivityLoginBinding::inflate

    private lateinit var loginViewModel: LoginViewModel

    @Inject lateinit var retrofitFactory: RetrofitFactory

    override fun setup() {
        setupViews()
        setupViewModel()
    }

    private fun setupViews() {
        (application as MyApplication).appComponent.inject(this)
        loginViewModel = ViewModelProvider(this, retrofitFactory).get(LoginViewModel::class.java)
        loginViewModel._loginStatu.postValue(RESPONSE_STATE.NOTTHING)

        binding.singupBtn.setOnClickListener {
            moveIntentResult(RegistrationActivity::class.java)
        }

        binding.loginBtn.setOnClickListener {
            var flag = true
            if (binding.loginEmailText.text?.length!! == 0) {
                binding.logindEmailLayout.error = "이메일을 입력해주세요."
                flag = false
            } else {
                binding.logindEmailLayout.error = ""
            }
            if (binding.loginPasswordText.text?.length!! == 0) {
                binding.loginPasswordLayout.error = "비밀번호를 입력해주세요."
                flag = false
            } else {
                binding.logindEmailLayout.error = ""
            }

            if(flag){
                loginViewModel.login(
                    null,
                    binding.loginEmailText.text.toString(),
                    binding.loginPasswordText.text.toString(),
                    LOGIN_STATE.LOCAL
                )
            }
        }

        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
                UserApiClient.instance.me { user, error ->
                    // 로그인 상태
                    var tokenID = user!!.id.toString()
                    UserManagerObject.setUpUser(tokenID, LOGIN_STATE.KAKAO)
                    while (tokenID.length < 24) tokenID += '0'
                    loginViewModel.login(
                        tokenID,
                        user.kakaoAccount!!.email.toString(),
                        "000000",
                        LOGIN_STATE.KAKAO
                    )
                }
            }
        }

        binding.kakaoOauthBtn.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }


    override fun resultActivity(activityResult: ActivityResult) {
        super.resultActivity(activityResult)
        if (activityResult.resultCode == RESULT_OK) {
            Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_LONG).show()
        }
    }


    private fun setupViewModel(){
        loginViewModel._loading.observe(this, {
            if(it){
                showLoadingAni()
            }else{
                dismissLoadingAni()
            }
        })
        loginViewModel._loginStatu.observe(this, {
            when (it) {
                RESPONSE_STATE.OK -> {
                    loginViewModel._loginStatu.postValue(RESPONSE_STATE.NOTTHING)
                    moveIntentAllClear(MainActivity::class.java)
                }
                RESPONSE_STATE.NOTTHING  ->{

                }
                else -> {
                    Toast.makeText(this, "이메일이나 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed >= 1500){
            lastTimeBackPressed = System.currentTimeMillis()
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_LONG).show()
        } else {
            finish()
        }
    }
}