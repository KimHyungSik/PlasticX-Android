package com.example.plasticx.user

import com.example.plasticx.utils.LOGIN_STATE

object UserManagerObject {
    var userId: String = ""
    var loginState: LOGIN_STATE = LOGIN_STATE.NO

    fun setUpUser(userId: String, loginStatuis: LOGIN_STATE){
        this.userId = userId
        this.loginState = loginStatuis
    }
}