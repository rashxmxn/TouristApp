package com.example.touristapp.ui.itinerary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.touristapp.data.ItineraryDao
import com.example.touristapp.model.Itinerary
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.UUID

class ItineraryViewModel(
    // Pass dao here
    private val itineraryDao: ItineraryDao,
    application: Application
) : ViewModel() {

    val allItineraries: LiveData<List<Itinerary>> = itineraryDao.getItineraries().asLiveData()

    fun getItinerary(id: Long): LiveData<Itinerary> = itineraryDao.getItinerary(id).asLiveData()

    fun addItinerary(
        name: String,
        address: String,
        hasVisited: Boolean,
        date: String,
        notes: String,
        image: String,
        database: DatabaseReference
    ) {
        val itinerary = Itinerary(
            name = name,
            address = address,
            hasVisited = hasVisited,
            date = date,
            notes = notes,
            image = image
        )
        database.child(UUID.randomUUID().toString()).setValue(itinerary)
        viewModelScope.launch {
            itineraryDao.insert(itinerary)
        }
    }

//    fun updateItinerary(
//        id: Long,
//        name: String,
//        address: String,
//        hasVisited: Boolean,
//        date: String,
//        notes: String
//    ) {
//        val itinerary = Itinerary(
//            id = id,
//            name = name,
//            address = address,
//            hasVisited = hasVisited,
//            date = date,
//            notes = notes
//        )
//        viewModelScope.launch(Dispatchers.IO) {
//            itineraryDao.update(itinerary)
//        }
//    }

    fun deleteItinerary(itinerary: Itinerary) {
        viewModelScope.launch(Dispatchers.IO) {
            itineraryDao.delete(itinerary)
        }
    }

    fun isValidEntry(name: String, address: String): Boolean {
        return name.isNotBlank() && address.isNotBlank()
    }
}


class ItineraryViewModelFactory(
    private val dao: ItineraryDao,
    private val application: Application
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItineraryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItineraryViewModel(dao, application = application) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}