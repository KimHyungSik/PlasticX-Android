package com.example.plasticx.user

import com.example.plasticx.utils.LOGIN_STATUIS

object UserManagerObject {
    var userId: String = ""
    var loginState: LOGIN_STATUIS = LOGIN_STATUIS.NO

    fun setUpUser(userId: String, loginStatuis: LOGIN_STATUIS){
        this.userId = userId
        this.loginState = loginStatuis
    }
}