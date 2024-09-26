package com.bayutb.core.domain.repository

import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User

interface RoomRepository {
    suspend fun register(user: User): ResultCode
    fun getUser(userName: String): User?
}