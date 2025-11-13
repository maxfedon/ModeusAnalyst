package com.asteroiddd.modeusanalyst.source.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asteroiddd.modeusanalyst.source.model.ModuleResult
import kotlinx.coroutines.flow.Flow

@Dao
interface ModuleResultDao {
    @Query("SELECT * FROM module_results")
    fun getAll(): Flow<List<ModuleResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(results: List<ModuleResult>)

    @Query("DELETE FROM module_results")
    suspend fun deleteAll()
}