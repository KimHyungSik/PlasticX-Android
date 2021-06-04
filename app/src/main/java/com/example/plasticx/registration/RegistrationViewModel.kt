package com.example.plasticx.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plasticx.retrofit.repository.RetrofitRepository
import com.example.plasticx.utils.RESPONSE_STATUIS

class RegistrationViewModel(val retrofitRepository: RetrofitRepository) : ViewModel(){
    var _loading: MutableLiveData<Boolean> = MutableLiveData()
    var _registerStatu: MutableLiveData<Boolean> = MutableLiveData()

    fun userRegister(name: String, email: String, password: String){
        _loading.postValue(true)
        val stat = retrofitRepository.userRegister(name, email, password)
        when(stat){
            RESPONSE_STATUIS.OK -> _registerStatu.postValue(true)
            else -> return
        }
    }
}