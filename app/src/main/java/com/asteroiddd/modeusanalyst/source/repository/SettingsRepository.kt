package com.asteroiddd.modeusanalyst.source.repository

import android.content.Context
import com.asteroiddd.modeusanalyst.source.database.AppDatabase
import com.asteroiddd.modeusanalyst.source.model.Setting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(context: Context) {

    private val settingDao = AppDatabase.getDatabase(context).settingDao()

    companion object {
        const val HOST = "host"
    }

    val hostFlow: Flow<String> = settingDao.get(HOST).map { it?.value ?: "10.232.66.94" }

    suspend fun saveHost(host: String) {
        settingDao.insert(Setting(HOST, host))
    }
}