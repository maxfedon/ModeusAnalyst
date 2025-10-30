package com.asteroiddd.modeusanalyst.source.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {

    private object PreferencesKeys {
        val OLLAMA_HOST = stringPreferencesKey("ollama_host")
    }

    val ollamaHostFlow: Flow<String> = context.dataStore.data
        .map {
            it[PreferencesKeys.OLLAMA_HOST] ?: ""
        }

    suspend fun saveOllamaHost(host: String) {
        context.dataStore.edit {
            it[PreferencesKeys.OLLAMA_HOST] = host
        }
    }
}