package com.bayutb.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.bayutb.core.domain.model.PreferenceKeys
import com.bayutb.core.domain.model.User
import com.bayutb.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {
    override suspend fun setUser(user: User) {
        dataStore.edit { pref->
            pref[PreferenceKeys.PREF_ID] = user.id
            pref[PreferenceKeys.PREF_USERNAME] = user.userName
            pref[PreferenceKeys.PREF_PASSWORD] = user.password
        }
    }

    override fun getLoggedInUser(): Flow<User?> {
        return dataStore.data.map { pref ->
            val id = pref[PreferenceKeys.PREF_ID]
            val userName = pref[PreferenceKeys.PREF_USERNAME]
            val password = pref[PreferenceKeys.PREF_PASSWORD]

            if (id != null && userName != null && password != null) {
                User(id, userName, password)
            } else {
                null
            }
        }
    }

    override suspend fun deleteSession() {
        dataStore.edit { pref ->
            pref.clear()
        }
    }
}