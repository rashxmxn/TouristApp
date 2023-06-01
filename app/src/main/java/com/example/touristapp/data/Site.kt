package com.example.touristapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "site_table")
data class Site(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "site_name")
    val site_name: String,

    @ColumnInfo(name = "site_city")
    val site_city: String,

    @ColumnInfo(name = "site_times")
    val site_times: String,

//    @ColumnInfo(name = "site_details")
//    val site_details: String

)