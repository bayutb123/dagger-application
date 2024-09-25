package com.bayutb.login.di

import com.bayutb.core.repository.DataStoreRepository
import com.bayutb.login.data.LoginDataSource
import com.bayutb.login.data.service.DummyAuth
import com.bayutb.login.domain.repository.LoginRepository
import com.bayutb.login.presentation.viewmodel.LoginViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LoginModule {

    @Provides
    fun provideDummyAuth() : DummyAuth {
        return DummyAuth()
    }

    @Provides
    fun provideLoginRepository(dummyAuth: DummyAuth, dataStoreRepository: DataStoreRepository) : LoginRepository {
        return LoginDataSource(dummyAuth, dataStoreRepository)
    }

    @Provides
    fun provideLoginViewModelFactory(repository: LoginRepository) : LoginViewModelFactory {
        return LoginViewModelFactory(repository)
    }
}