package com.bayutb.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bayutb.core.domain.model.User

@Dao
interface DaggerDao {

    @Insert
    suspend fun insert(entity: User)

    @Query("SELECT * FROM user WHERE userName=:userName")
    fun checkUsernameAvailability(userName: String): User?

    @Query("SELECT * FROM user WHERE userName=:userName")
    fun validateUser(userName: String): User?
}