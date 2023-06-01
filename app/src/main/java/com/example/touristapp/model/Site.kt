package com.example.touristapp.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import java.util.*

@Entity(tableName = "site_database")
class Site (
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val city: String,
    val category: String, //ie. park, museum, historical landmark,
    val latitude: Double,
    val longitude: Double,
    val details: String
)