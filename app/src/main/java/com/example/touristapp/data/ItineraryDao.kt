package com.example.touristapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.touristapp.model.Itinerary
import kotlinx.coroutines.flow.Flow


@Dao
interface ItineraryDao {

    @Query("SELECT * FROM itinerary_database ORDER BY name ASC")
    fun getItineraries(): Flow<List<Itinerary>>

    @Query("SELECT * from itinerary_database WHERE id = :id")
    fun getItinerary(id: Long): Flow<Itinerary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itinerary: Itinerary)

    @Update
    suspend fun update(itinerary: Itinerary)

    @Delete
    suspend fun delete(itinerary: Itinerary)
}