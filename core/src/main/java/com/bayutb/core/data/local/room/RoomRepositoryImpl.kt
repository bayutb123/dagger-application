package com.bayutb.core.data.local.room

import com.bayutb.core.app.EncryptionHelper
import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.core.domain.model.encryptPassword
import com.bayutb.core.repository.RoomRepository
import kotlinx.coroutines.flow.Flow

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

    override fun validateUser(user: User): Flow<User?> {
        return dao.getUser(user.userName, EncryptionHelper.decrypt(user.password))
    }

}