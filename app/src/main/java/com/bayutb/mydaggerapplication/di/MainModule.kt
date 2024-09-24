package com.bayutb.mydaggerapplication.di

import com.bayutb.core.repository.DataStoreRepository
import com.bayutb.mydaggerapplication.MainViewModelProvider
import dagger.Module
import dagger.Provides

@Module
object MainModule {
    @Provides
    fun provideMainViewModelProvider(dataStoreRepository: DataStoreRepository) : MainViewModelProvider {
        return MainViewModelProvider(dataStoreRepository)
    }
}