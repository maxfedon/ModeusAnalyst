package com.asteroiddd.modeusanalyst.source.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth")
data class Auth(
    @PrimaryKey val id: Int = 1,
    val username: String,
    val password: String
)