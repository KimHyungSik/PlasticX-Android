package com.example.plasticx.main.listfragment

import android.util.Log
import com.example.plasticx.retrofit.repository.RetrofitRepository
import com.example.plasticx.user.UserManagerObject
import javax.inject.Inject

class TumblerViewModel @Inject constructor(val retrofitRepository: RetrofitRepository) {

    val TAG = "TumblerViewModel - 로그"
    
    fun getTumblerList() = retrofitRepository
        .getUserTumblerList(UserManagerObject.userId)

}