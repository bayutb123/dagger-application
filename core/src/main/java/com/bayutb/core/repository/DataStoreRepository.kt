package com.bayutb.core.repository

import com.bayutb.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setUser(user: User)
    fun getUser(): Flow<User?>
}