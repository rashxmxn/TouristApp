package com.example.touristapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.touristapp.data.Site

@Database(entities = [Site::class], version = 1, exportSchema = false)
abstract class SiteRoomDatabase : RoomDatabase() {

    abstract fun siteDao(): SiteDao

    companion object {
        @Volatile
        private var INSTANCE: SiteRoomDatabase? = null
        fun getDatabase(context: Context): SiteRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    SiteRoomDatabase::class.java,
                    "site_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}