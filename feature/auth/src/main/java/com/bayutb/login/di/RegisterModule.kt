package com.bayutb.login.di

import com.bayutb.core.domain.repository.RoomRepository
import com.bayutb.login.data.RegisterDataSource
import com.bayutb.login.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides

@Module
object RegisterModule {
    @Provides
    fun provideRegisterRepository(roomRepository: RoomRepository) : RegisterRepository {
        return RegisterDataSource(roomRepository)
    }
}