package com.bayutb.mydaggerapplication.di

import com.bayutb.core.di.AppComponent
import com.bayutb.core.domain.repository.DataStoreRepository
import com.bayutb.mydaggerapplication.HomeFragment
import dagger.Component

@MainScope
@Component(dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(homeFragment: HomeFragment)

    fun provideDataStoreRepository(): DataStoreRepository
}