package com.asteroiddd.modeusanalyst.source.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asteroiddd.modeusanalyst.source.model.Auth
import com.asteroiddd.modeusanalyst.source.model.ModuleResult
import com.asteroiddd.modeusanalyst.source.model.Setting

@Database(entities = [ModuleResult::class, Setting::class, Auth::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun authDao(): AuthDao
    abstract fun settingDao(): SettingDao
    abstract fun moduleResultDao(): ModuleResultDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "modeus_analyst_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}