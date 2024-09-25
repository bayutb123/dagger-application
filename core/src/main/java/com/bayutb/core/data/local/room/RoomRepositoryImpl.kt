package com.bayutb.core.data.local.room

import android.util.Log
import com.bayutb.core.app.EncryptionHelper
import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.core.domain.model.encryptPassword
import com.bayutb.core.repository.RoomRepository

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

    override fun validateUser(user: User): User? {
        val result = dao.checkUsernameAvailability(user.userName)
        return if (result != null) {
            if (EncryptionHelper.decrypt(result.password) == user.password) {
                result
            } else {
                null
            }
        } else {
            null
        }
    }
}
