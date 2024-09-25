package com.bayutb.core.repository

import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun register(user: User): ResultCode
    fun validateUser(user: User): User?
}