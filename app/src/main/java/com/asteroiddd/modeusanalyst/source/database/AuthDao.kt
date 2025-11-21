package com.asteroiddd.modeusanalyst.source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asteroiddd.modeusanalyst.source.model.Auth
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {
    @Query("SELECT * FROM auth WHERE id = 1")
    fun getAuth(): Flow<Auth?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAuth(auth: Auth)
}