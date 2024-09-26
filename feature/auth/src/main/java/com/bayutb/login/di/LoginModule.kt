package com.bayutb.login.di

import com.bayutb.core.domain.repository.DataStoreRepository
import com.bayutb.core.domain.repository.RoomRepository
import com.bayutb.login.data.LoginDataSource
import com.bayutb.login.data.service.DummyAuth
import com.bayutb.login.domain.repository.LoginRepository
import com.bayutb.login.presentation.viewmodel.LoginViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object LoginModule {

    @Provides
    fun provideDummyAuth() : DummyAuth {
        return DummyAuth()
    }

    @Provides
    fun provideLoginRepository(roomRepository: RoomRepository) : LoginRepository {
        return LoginDataSource(roomRepository)
    }

    @Provides
    fun provideLoginViewModelFactory(repository: LoginRepository, dataStoreRepository: DataStoreRepository) : LoginViewModelFactory {
        return LoginViewModelFactory(repository, dataStoreRepository)
    }
}