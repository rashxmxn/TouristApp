package com.example.touristapp.data

import android.content.ClipData
import androidx.room.*
//import java.util.concurrent.Flow
import kotlinx.coroutines.flow.Flow

@Dao
interface SiteDao {
    @Query("SELECT * from site_table ORDER BY site_city ASC")
    fun getItems(): Flow<List<Site>>

    @Query("SELECT * from site_table WHERE id = :id")
    fun getItem(id: Int): Flow<Site>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Site: Site)

    @Update
    suspend fun update(Site: Site)

    @Delete
    suspend fun delete(Site: Site)
}