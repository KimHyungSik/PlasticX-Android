package com.plasticxv.plasticx.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plasticxv.plasticx.MyApplication
import com.plasticxv.plasticx.retrofit.repository.RetrofitRepository
import com.plasticxv.plasticx.user.UserManagerObject
import com.plasticxv.plasticx.utils.LOGIN_STATE
import com.plasticxv.plasticx.utils.PreferencesManager
import com.plasticxv.plasticx.utils.RESPONSE_STATE
import com.plasticxv.plasticx.utils.Utility
import javax.inject.Inject

class LoginViewModel @Inject constructor(val retrofitRepository: RetrofitRepository): ViewModel() {
    var _loading: MutableLiveData<Boolean> = MutableLiveData()
    var _loginStatu: MutableLiveData<RESPONSE_STATE> = MutableLiveData()

    fun login(_id:String?, email: String, password: String, loginState: LOGIN_STATE){
        _loading.postValue(true)

        retrofitRepository.userRepository().userLogin(_id, email, password, loginState, completion = {
            _responseState, s->
            when(_responseState){
                RESPONSE_STATE.OK->{
                    _loginStatu.postValue(_responseState)
                    PreferencesManager.setString(MyApplication.instance, Utility.USER_ID_KEY, s!!)
                    UserManagerObject.setUpUser(s, loginState)
                    _loading.postValue(false)
                }
                else ->  {
                    _loginStatu.postValue(_responseState)
                    _loading.postValue(false)
                }
            }

        })
    }
}