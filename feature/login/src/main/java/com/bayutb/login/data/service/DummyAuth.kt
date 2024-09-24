package com.bayutb.login.data.service

import com.bayutb.login.domain.model.LoginResult
import com.bayutb.login.domain.model.ResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.login.domain.payload.LoginPayload

class DummyAuth {
    private val _validUser = User("username", "password")
    fun login(loginPayload: LoginPayload): LoginResult {
        return LoginResult(
            resultCode = loginPayload.validate(),
            user = User(loginPayload.userName, loginPayload.password)
        )
    }

    private fun LoginPayload.validate() : ResultCode {
        return if (this.userName == _validUser.userName && this.password == _validUser.password) {
            ResultCode.SUCCESS
        } else {
            ResultCode.FAILED
        }
    }
}
