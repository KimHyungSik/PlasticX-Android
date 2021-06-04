package com.example.plasticx.retrofit.repository

import com.example.plasticx.retrofit.user.UserRetrofitManager
import com.example.plasticx.utils.RESPONSE_STATUIS

class RetrofitRepository {
    fun userRegister(name: String, email: String, password: String): RESPONSE_STATUIS {
        var responseStatuis = RESPONSE_STATUIS.ERROR
        UserRetrofitManager.instance.userRegister(name, email, password, completion = {
            _responseStatuis, s ->
            responseStatuis = _responseStatuis
        })
        return responseStatuis
    }
}