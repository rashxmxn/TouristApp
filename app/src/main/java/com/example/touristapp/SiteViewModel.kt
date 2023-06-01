package com.example.touristapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.touristapp.data.Site
import com.example.touristapp.data.SiteDao
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class InventoryViewModel(private val siteDao: SiteDao) : ViewModel() {

    /**
     * Inserts the new Item into database.
     */
    fun addNewSite(id: Int, site_name: String, site_city: String, site_times: String) {
        val newSite = getNewSiteEntry(id,site_name, site_city, site_times)
        insertItem(newSite)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(site: Site) {
        viewModelScope.launch {
            siteDao.insert(site)
        }
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid(id: Int, site_name: String, site_city: String, site_times: String): Boolean {
        if (site_city.isBlank() || site_name.isBlank() || site_times.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewSiteEntry(id: Int, site_name: String, site_city: String, site_times: String): Site {
        return Site(
            id = id,
            site_name = site_name,
            site_city = site_city,
            site_times = site_times
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
//class SiteViewModelFactory(private val siteDao: SiteDao) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SiteViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return InventoryViewModel(siteDao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

class SiteViewModel {

}
