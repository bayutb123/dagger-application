package com.bayutb.core.di

import android.app.Application
import com.bayutb.core.modules.LocalStorageModule
import com.bayutb.core.modules.NetworkModule
import com.bayutb.core.repository.DataStoreRepository
import com.bayutb.core.repository.RoomRepository
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