package com.bayutb.login.data

import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.core.repository.RoomRepository
import com.bayutb.login.domain.repository.RegisterRepository

class RegisterDataSource(
    private val roomRepository: RoomRepository
) : RegisterRepository {
    override suspend fun register(user: User): ResultCode {
        return roomRepository.register(user)
    }
}