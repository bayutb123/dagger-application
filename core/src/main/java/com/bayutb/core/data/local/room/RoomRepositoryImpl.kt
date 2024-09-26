package com.bayutb.core.data.local.room

import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.core.domain.model.encryptPassword
import com.bayutb.core.domain.repository.RoomRepository

class RoomRepositoryImpl(
    private val dao: DaggerDao
) : RoomRepository {
    override suspend fun register(user: User): ResultCode {
        val available = dao.checkUsernameAvailability(user.userName) == null
        if (available) {
            dao.insert(user.encryptPassword())
            return ResultCode.SUCCESS
        } else {
            return ResultCode.FAILED
        }
    }

    override fun getUser(userName:String): User? {
        return dao.validateUser(userName)
    }
}
