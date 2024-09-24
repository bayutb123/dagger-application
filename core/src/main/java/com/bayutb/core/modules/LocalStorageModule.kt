package com.bayutb.core.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.bayutb.core.data.local.DataStoreRepositoryImpl
import com.bayutb.core.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class LocalStorageModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideDataStorePref(): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler {
                emptyPreferences()
            },
            migrations = listOf(SharedPreferencesMigration(context, "dagger-pref")),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile("dagger-pref") }
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>) : DataStoreRepository {
        return DataStoreRepositoryImpl(dataStore)
    }
}