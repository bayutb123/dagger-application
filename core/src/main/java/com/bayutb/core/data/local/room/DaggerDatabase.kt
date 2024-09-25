package com.bayutb.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bayutb.core.domain.model.User

@Database(entities = [User::class], version = 1)
abstract class DaggerDatabase(): RoomDatabase() {
    abstract fun dao(): DaggerDao
}