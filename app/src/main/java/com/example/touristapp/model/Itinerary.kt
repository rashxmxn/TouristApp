package com.example.touristapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import androidx.room.PrimaryKey

@Entity(tableName = "itinerary_database")
@Parcelize
data class Itinerary(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val address: String,
    @ColumnInfo(name = "has_visited") val hasVisited: Boolean,
    val date: String,
    val notes: String?,
    val image: String
) :Parcelable{
    constructor() : this(0, "", "", false, "", null, "")
}