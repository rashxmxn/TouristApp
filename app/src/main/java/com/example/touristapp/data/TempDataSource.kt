package com.example.touristapp.data

import com.example.touristapp.R
import com.example.touristapp.model.City

class TempDatasource() {
    fun loadCities(): List<City> {
        return listOf<City>(
            City(R.drawable.north_kazakhstan, "North Kazakhstan"),
        )
    }
}
