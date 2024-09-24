package com.bayutb.login.data

import com.bayutb.login.data.service.DummyAuth
import com.bayutb.login.domain.model.LoginResult
import com.bayutb.login.domain.payload.LoginPayload
import com.bayutb.login.domain.repository.LoginRepository

class LoginDataSource(private val dummyAuth: DummyAuth) : LoginRepository {

    override fun login(payload: LoginPayload) : LoginResult {
        return dummyAuth.login(payload)
    }
}