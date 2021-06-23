package com.example.plasticx.user

import com.example.plasticx.utils.LOGIN_STATE

// 유저 정보 저장
object UserManagerObject {
    var userId: String = ""
    var userName: String = ""
    var loginState: LOGIN_STATE = LOGIN_STATE.NO

    fun setUpUser(userId: String, loginStatuis: LOGIN_STATE){
        this.userId = userId
        this.loginState = loginStatuis
    }
}