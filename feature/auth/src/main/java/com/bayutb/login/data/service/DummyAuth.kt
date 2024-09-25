package com.bayutb.login.data.service

import com.bayutb.login.domain.model.LoginResult
import com.bayutb.login.domain.model.LoginResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.login.domain.payload.LoginPayload

class DummyAuth {
    private val _validUser = User(0, "username", "password")
    fun login(loginPayload: LoginPayload): LoginResult {
        return LoginResult(
            loginResultCode = loginPayload.validate(),
            user = User(0, loginPayload.userName, loginPayload.password)
        )
    }

    private fun LoginPayload.validate() : LoginResultCode {
        return if (this.userName == _validUser.userName && this.password == _validUser.password) {
            LoginResultCode.SUCCESS
        } else {
            LoginResultCode.FAILED
        }
    }
}
