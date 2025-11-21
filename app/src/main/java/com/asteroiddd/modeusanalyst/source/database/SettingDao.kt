package com.asteroiddd.modeusanalyst.source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asteroiddd.modeusanalyst.source.model.Setting
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {
    @Query("SELECT * FROM settings WHERE `key` = :key")
    fun get(key: String): Flow<Setting?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(setting: Setting)
}