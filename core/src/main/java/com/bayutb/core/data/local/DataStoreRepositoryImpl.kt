package com.bayutb.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.bayutb.core.domain.model.PreferenceKeys
import com.bayutb.core.domain.model.User
import com.bayutb.core.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {
    override suspend fun setUser(user: User) {
        dataStore.edit { pref->
            pref[PreferenceKeys.PREF_USERNAME] = user.userName
            pref[PreferenceKeys.PREF_PASSWORD] = user.password
        }
    }

    override fun getUser(): Flow<User?> {
        return dataStore.data.map { pref ->
            val userName = pref[PreferenceKeys.PREF_USERNAME]
            val password = pref[PreferenceKeys.PREF_PASSWORD]

            if (userName != null && password != null) {
                User(userName, password)
            } else {
                null
            }
        }
    }
}