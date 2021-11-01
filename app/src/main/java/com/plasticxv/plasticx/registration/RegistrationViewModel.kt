package com.plasticxv.plasticx.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plasticxv.plasticx.retrofit.repository.RetrofitRepository
import com.plasticxv.plasticx.user.UserManagerObject
import com.plasticxv.plasticx.utils.RESPONSE_STATE
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