package com.bayutb.core.di

import android.app.Application
import com.bayutb.core.di.modules.LocalStorageModule
import com.bayutb.core.di.modules.NetworkModule
import com.bayutb.core.domain.repository.DataStoreRepository
import com.bayutb.core.domain.repository.RoomRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, LocalStorageModule::class])
interface AppComponent {
    fun retrofit(): Retrofit
    fun dataStoreRepository(): DataStoreRepository
    fun roomRepository(): RoomRepository

    interface AppComponentProvider {
        fun getComponent(): AppComponent
    }
}

fun Application.getComponent() : AppComponent {
    return (applicationContext as AppComponent.AppComponentProvider).getComponent()
}