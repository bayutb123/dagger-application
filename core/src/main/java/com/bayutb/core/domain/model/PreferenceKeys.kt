package com.bayutb.core.domain.model

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val PREF_USERNAME = stringPreferencesKey("pref-username")
    val PREF_PASSWORD = stringPreferencesKey("pref-password")
}