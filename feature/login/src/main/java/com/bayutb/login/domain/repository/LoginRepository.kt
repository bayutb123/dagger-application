package com.bayutb.login.domain.repository

import com.bayutb.login.domain.model.LoginResult
import com.bayutb.login.domain.payload.LoginPayload

interface LoginRepository {
    suspend fun login(payload: LoginPayload) : LoginResult
}