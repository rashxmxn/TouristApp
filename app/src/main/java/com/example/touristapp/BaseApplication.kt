/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.touristapp

import android.app.Application
import com.example.touristapp.data.ItineraryDatabase
import com.example.touristapp.data.SiteRoomDatabase
import com.example.touristapp.ui.itinerary.viewmodel.ItineraryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BaseApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ItineraryDatabase.getDatabase(this, applicationScope)}
    //addition
    val repository by lazy { ItineraryViewModel(database.itineraryDao(),this) }

    val site_database by lazy { SiteRoomDatabase.getDatabase(this) }
}