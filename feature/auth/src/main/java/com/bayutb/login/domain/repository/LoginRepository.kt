package com.bayutb.login.domain.repository

import com.bayutb.login.domain.model.LoginResult
import com.bayutb.login.domain.payload.LoginPayload
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(payload: LoginPayload) : Flow<LoginResult>
}