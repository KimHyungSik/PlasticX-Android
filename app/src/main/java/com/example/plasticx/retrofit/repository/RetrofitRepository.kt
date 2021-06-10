package com.example.plasticx.retrofit.repository

import com.example.plasticx.MyApplication
import com.example.plasticx.retrofit.user.UserRetrofitManager
import com.example.plasticx.utils.PreferencesManager
import com.example.plasticx.utils.RESPONSE_STATUIS
import com.example.plasticx.utils.Utility.USER_ID_KEY

class RetrofitRepository {
    fun userRegister(name: String, email: String, password: String): RESPONSE_STATUIS {
        var responseStatuis = RESPONSE_STATUIS.ERROR
        UserRetrofitManager.instance.userRegister(name, email, password, completion = {
            _responseStatuis, s ->
            responseStatuis = _responseStatuis
            PreferencesManager.setString(MyApplication.instance, USER_ID_KEY, s!!)
        })
        return responseStatuis
    }
}