package com.example.plasticx.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plasticx.MyApplication
import com.example.plasticx.retrofit.repository.RetrofitRepository
import com.example.plasticx.retrofit.user.UserRetrofitManager
import com.example.plasticx.user.UserManagerObject
import com.example.plasticx.utils.LOGIN_STATE
import com.example.plasticx.utils.PreferencesManager
import com.example.plasticx.utils.RESPONSE_STATE
import com.example.plasticx.utils.Utility
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(val retrofitRepository: RetrofitRepository) : ViewModel(){
    var _loading: MutableLiveData<Boolean> = MutableLiveData()
    var _registerStatu: MutableLiveData<RESPONSE_STATE> = MutableLiveData()

    fun userRegister(name: String, email: String, password: String){
        _loading.postValue(true)

        retrofitRepository.userRepository().userRegister(name, email, password, completion = {
                _responseState, s ->
            when(_responseState){
                RESPONSE_STATE.OK -> {
                    UserManagerObject.userName = name
                    _registerStatu.postValue(_responseState)
                    PreferencesManager.setString(MyApplication.instance, Utility.USER_ID_KEY, s!!)
                    UserManagerObject.setUpUser(s, LOGIN_STATE.LOCAL)
                    _loading.postValue(false)
                }
                else ->  {
                    _registerStatu.postValue(_responseState)
                    _loading.postValue(false)
                }
            }

        })
    }
}