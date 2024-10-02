package com.bayutb.login.di

import com.bayutb.core.domain.repository.RoomRepository
import com.bayutb.login.data.LoginDataSource
import com.bayutb.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides

@Module
object LoginModule {
    @Provides
    fun provideLoginRepository(roomRepository: RoomRepository) : LoginRepository {
        return LoginDataSource(roomRepository)
    }
}