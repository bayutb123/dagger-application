package com.bayutb.core.di

import com.bayutb.core.modules.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun retrofit(): Retrofit
}