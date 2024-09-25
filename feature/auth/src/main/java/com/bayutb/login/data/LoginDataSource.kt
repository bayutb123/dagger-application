package com.bayutb.login.data

import com.bayutb.core.domain.model.User
import com.bayutb.core.repository.DataStoreRepository
import com.bayutb.login.data.service.DummyAuth
import com.bayutb.login.domain.model.LoginResult
import com.bayutb.login.domain.model.LoginResultCode
import com.bayutb.login.domain.payload.LoginPayload
import com.bayutb.login.domain.repository.LoginRepository

class LoginDataSource(
    private val dummyAuth: DummyAuth,
    private val dataStoreRepository: DataStoreRepository
) : LoginRepository {

    override suspend fun login(payload: LoginPayload) : LoginResult {
        val result = dummyAuth.login(payload)
        if (result.loginResultCode == LoginResultCode.SUCCESS) {
            dataStoreRepository.setUser(User(id = 0, userName = result.user.userName, password = result.user.password))
        }
        return result
    }
}