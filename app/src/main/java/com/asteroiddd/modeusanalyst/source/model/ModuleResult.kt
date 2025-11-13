package com.asteroiddd.modeusanalyst.source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "module_results")
data class ModuleResult(
    @PrimaryKey val name: String,
    val score: String,
    val mark: String
)