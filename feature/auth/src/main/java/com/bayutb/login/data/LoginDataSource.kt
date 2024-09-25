package com.bayutb.login.data

import android.util.Log
import com.bayutb.core.domain.model.User
import com.bayutb.core.repository.DataStoreRepository
import com.bayutb.core.repository.RoomRepository
import com.bayutb.login.data.service.DummyAuth
import com.bayutb.login.domain.model.LoginResult
import com.bayutb.login.domain.model.LoginResultCode
import com.bayutb.login.domain.payload.LoginPayload
import com.bayutb.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LoginDataSource(
    private val roomRepository: RoomRepository,
    private val dataStoreRepository: DataStoreRepository
) : LoginRepository {

    override suspend fun login(payload: LoginPayload): Flow<LoginResult> {
        val result = roomRepository.validateUser(User(0, payload.userName, payload.password))
        Log.d("Dagger", result.toString())

        return flow {
            if (result != null) {
                emit(
                    LoginResult(
                        LoginResultCode.SUCCESS,
                        User(result.id, result.userName, result.password)
                    )
                )
                dataStoreRepository.setUser(user = User(result.id, result.userName, result.password))
            } else {
                emit(
                    LoginResult(
                        LoginResultCode.FAILED,
                        User(0, payload.userName, payload.password)
                    )
                )
            }
        }
    }
}