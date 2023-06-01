package com.example.touristapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.touristapp.model.Itinerary
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Itinerary::class], version = 1, exportSchema = false)
abstract class ItineraryDatabase : RoomDatabase() {
    abstract fun itineraryDao() : ItineraryDao

    companion object {
        @Volatile
        private var INSTANCE: ItineraryDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ItineraryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    ItineraryDatabase::class.java,
                    "itinerary_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}