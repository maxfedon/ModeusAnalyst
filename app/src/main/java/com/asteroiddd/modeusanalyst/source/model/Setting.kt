package com.asteroiddd.modeusanalyst.source.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Setting(
    @PrimaryKey
    val key: String,
    val value: String
)