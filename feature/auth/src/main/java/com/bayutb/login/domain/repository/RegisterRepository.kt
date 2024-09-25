package com.bayutb.login.domain.repository

import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User

interface RegisterRepository {
    suspend fun register(user: User): ResultCode
}