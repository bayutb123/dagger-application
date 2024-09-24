package com.bayutb.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bayutb.core.modules.LocalStorageModule
import com.bayutb.core.modules.NetworkModule
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [NetworkModule::class, LocalStorageModule::class])
interface AppComponent {
    fun retrofit(): Retrofit
    fun preferenceDataStore(): DataStore<Preferences>
}