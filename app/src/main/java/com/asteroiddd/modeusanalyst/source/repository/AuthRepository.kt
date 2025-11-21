package com.asteroiddd.modeusanalyst.source.repository

import android.content.Context
import com.asteroiddd.modeusanalyst.source.database.AppDatabase
import com.asteroiddd.modeusanalyst.source.model.Auth
import kotlinx.coroutines.flow.Flow

class AuthRepository(context: Context) {

    private val authDao = AppDatabase.getDatabase(context).authDao()

    fun getAuth(): Flow<Auth?> = authDao.getAuth()

    suspend fun saveAuth(auth: Auth) {
        authDao.saveAuth(auth)
    }
}