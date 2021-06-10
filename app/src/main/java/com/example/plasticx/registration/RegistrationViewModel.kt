package com.example.plasticx.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plasticx.MyApplication
import com.example.plasticx.retrofit.repository.RetrofitRepository
import com.example.plasticx.retrofit.user.UserRetrofitManager
import com.example.plasticx.user.UserManagerObject
import com.example.plasticx.utils.LOGIN_STATUIS
import com.example.plasticx.utils.PreferencesManager
import com.example.plasticx.utils.RESPONSE_STATUIS
import com.example.plasticx.utils.Utility
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(val retrofitRepository: RetrofitRepository) : ViewModel(){
    var _loading: MutableLiveData<Boolean> = MutableLiveData()
    var _registerStatu: MutableLiveData<RESPONSE_STATUIS> = MutableLiveData()

    fun userRegister(name: String, email: String, password: String){
        _loading.postValue(true)

        UserRetrofitManager.instance.userRegister(name, email, password, completion = {
                _responseStatuis, s ->
            when(_responseStatuis){
                RESPONSE_STATUIS.OK -> {
                    _registerStatu.postValue(_responseStatuis)
                    PreferencesManager.setString(MyApplication.instance, Utility.USER_ID_KEY, s!!)
                    UserManagerObject.userId = s
                    UserManagerObject.loginState = LOGIN_STATUIS.LOCAL
                    _loading.postValue(false)
                }
                else ->  {
                    _registerStatu.postValue(_responseStatuis)
                    _loading.postValue(false)
                }
            }

        })
    }
}