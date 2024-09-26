package com.bayutb.core.domain.repository

import com.bayutb.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setUser(user: User)
    fun getLoggedInUser(): Flow<User?>
    suspend fun deleteSession()
}