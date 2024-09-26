package com.bayutb.login.data

import com.bayutb.core.app.EncryptionHelper
import com.bayutb.core.domain.model.User
import com.bayutb.core.domain.repository.RoomRepository
import com.bayutb.login.domain.model.LoginResult
import com.bayutb.login.domain.model.LoginResultCode
import com.bayutb.login.domain.payload.LoginPayload
import com.bayutb.login.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginDataSource(
    private val roomRepository: RoomRepository
) : LoginRepository {
    override suspend fun login(payload: LoginPayload): LoginResult {
        return withContext(Dispatchers.IO) {
            val result = roomRepository.getUser(payload.userName)
            val decryptedPassword = result?.password?.let { EncryptionHelper.decrypt(it) }

            if (result != null && decryptedPassword == payload.password) {
                LoginResult(
                    loginResultCode = LoginResultCode.SUCCESS,
                    user = User(result.id, result.userName, result.password)
                )
            } else {
                LoginResult(
                    loginResultCode = LoginResultCode.FAILED,
                    user = User(0, payload.userName, payload.password)
                )
            }
        }
    }
}