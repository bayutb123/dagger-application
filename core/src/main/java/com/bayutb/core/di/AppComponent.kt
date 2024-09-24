package com.bayutb.core.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bayutb.core.modules.LocalStorageModule
import com.bayutb.core.modules.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, LocalStorageModule::class])
interface AppComponent {
    fun retrofit(): Retrofit
    fun preferenceDataStore(): DataStore<Preferences>

    interface AppComponentProvider {
        fun getComponent(): AppComponent
    }
}

fun Application.getComponent() : AppComponent {
    return (applicationContext as AppComponent.AppComponentProvider).getComponent()
}