package com.example.visualnovel.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserNickname(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserNickname")
        val USER_NICKNAME_KEY = stringPreferencesKey("user_nickname")
    }

    val getNickname: Flow<String?> =
        context.dataStore.data.map { preferences -> preferences[USER_NICKNAME_KEY] ?: "" }

    suspend fun saveNickname(name: String) {
        context.dataStore.edit { preferences -> preferences[USER_NICKNAME_KEY] = name }
    }
}