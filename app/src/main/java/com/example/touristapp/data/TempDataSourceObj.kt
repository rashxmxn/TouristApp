package com.example.touristapp.data

import com.example.touristapp.R
import com.example.touristapp.model.City
import com.example.touristapp.model.Site
import java.util.*


object TempDataSourceObj {
    val wishlist: MutableList<Site> = mutableListOf()
    val cities: List<City> = listOf(
        City(
            R.drawable.south_kazakhstan,
            "South Kazakhstan"
        ),

        City(
            R.drawable.north_kazakhstan,
            "North Kazakhstan"
        ),
        City(
            R.drawable.central_kazakhstan,
            "Central Kazakhstan"
        ),
        City(
            R.drawable.west_kazakhstan,
            "West Kazakhstan"
        )
    )

    val sites: List<Site> = listOf(
        Site(
            R.drawable.altyn_emel,
            "Altyn Emel",
            "South Kazakhstan",
            "Historical Landmark",
            30.274665,
            -97.740350,
            "\n" +
                    "The mysterious sound coming from the depths of a huge sandy mountain is all a mysterious dune. " +
                    "\"Singing Dune\" even entered the list of nominees for the world competition \"Seven new Wonders of Nature\". " +
                    "This competition was held by an independent Swiss organization from 2007 to 2011.\n"
        ),
        Site(
            R.drawable.kolsai,
            "Kolsai Lakes",
            "South Kazakhstan",
            "Historical Landmark",
            30.261738,
            -97.745163,
            "Kolsai Lakes are one of the wonders of Kazakhstan. Translated from the Kazakh language," +
                    " ”kolsai” means “gorge of lakes\". Three Kolsai lakes are located in the eastern part of " +
                    "the Kungei Alatau ridge, which is 300 km from Almaty."
        ),
        Site(
            R.drawable.city_of_nomads,
            "City of Nomads",
            "South Kazakhstan",
            "Historical Landmark",
            30.284918,
            -97.734057,
            "The city of nomads on the Ili River is located near Almaty. It attracts with its beauty and helps to" +
                    " plunge into the history of the Middle Ages. These sets were built in 2004 for the movie \"Nomad\". So, " +
                    "among the Kazakh steppe, another attraction was formed. The fortress includes an entire city in its complex. " +
                    "You will see the museum-monument and feel as if you have been transported back many centuries. " +
                    "Passing through the main gate, every tourist gets into an old town with houses and religious buildings. " +
                    "You will see a zindanam, a battering ram, a well and a gallows. Panoramic photos can be taken on the upper platform. " +
                    "The fortress is surrounded by the majestic beauty of Mother Nature. You will also have the opportunity to swim in the river" +
                    " Or and relax by its shores."
        ),
        Site(
            R.drawable.charyn,
            "Charyn Canyon",
            "South Kazakhstan",
            "Historical Landmark",
            30.285823,
            -97.729261,
            "Charyn Canyon is one of the most unique objects of nature. Under the influence of thousands " +
                    "of years of weathering of sedimentary rocks, the relief took the shape of isolated rocks, " +
                    "columns and towers. This is a natural monument, which is about 12 million years old. The most " +
                    "interesting place for tourists is the so-called Valley of Castles. In the bosom of the canyon, " +
                    "a grove of a relict species of ash that survived the glaciation era has been preserved. There " +
                    "is another similar grove only in North America. Landscape diversity determines the diversity of " +
                    "flora and fauna. The Charyn Canyon resembles the Grand Canyon of Colorado (Grand Canyon) in North " +
                    "America. Although the Charyn Canyon is inferior to it in size, but the beauty of the \"Valley of Castles\" is unique."
        ),
        Site(
            R.drawable.karkaraly,
            "Karkaraly National Park",
            "Central Kazakhstan",
            "Historical Landmark",
            48.860294,
            2.338629,
            "During this tour you will visit the Karkaraly nature park and see numerous archaeological sites: the ancient settlement" +
                    " of kent, scythian mounds and the kent palace – a monument of the era of the dzungarian-kazakh wars."
        ),
        Site(
            R.drawable.kogralzhyn,
            "Korgalzhyn Nature Reserve",
            "North Kazakhstan",
            "Historical Landmark",
            39.915588,
            116.396980,
            "The excursion to the Korgalzhyn state nature reserve will undoubtedly appeal to nature lovers. " +
                    "Located at the crossroads of migratory routes of migratory birds, the reserve is one of the key ornithological " +
                    "territories of international importance."
        ),
        Site(
            R.drawable.zerendi,
            "Zerendi",
            "North Kazakhstan",
            "Historical Landmark",
            21.638950,
            -157.919858,
            "Zerendi is a natural park in which there are mountains and hills covered with pine forest, rivers and thousands of lakes, " +
                    "the most famous of them has the same name."
        ),
        Site(
            R.drawable.ustyurt,
            "Ustyurt:Bozzhira,Bakty & Kyzylkup",
            "West Kazakhstan",
            "Historical Landmark",
            36.112310,
            -115.170397,
            "The hills of Kyzylkup are called \"stone tiramisu\" because of the surprisingly bright layers of chalk: thick cherry, yellow and white. " +
                    "Mount Bokty complements this palette with gray-green shades. The rocks bordering the Bozzhira Valley are considered to be among the " +
                    "TOP 10 best landscapes in the world in terms of beauty.\n" +
                    "\n"+
                    "These places have not yet been trampled by crowds of tourists, so during the trip you can see wild argali grazing on the cliffs of the " +
                    "plateau. The structures of ancient hunters have been preserved — the so-called \"arrows of Ustyurt\", which allowed hunting timid argali " +
                    "before the appearance of firearms.\n"
        ),
        Site(
            R.drawable.tub_karagan,
            "Tub-Karagan",
            "West Kazakhstan",
            "Historical Landmark",
            40.761509,
            -73.978271,
            "Most of the territory of the mangystau region is a desert sung in the poems of hundreds of poets, whose sands, whispering under the " +
                    "touch of the wind, remember distant times. No wonder there are so many ancient mosques, necropolises and other historical places in mangystau."
        )
    )

    fun searchSites(searchTerm: String): List<Site> {
        val s = searchTerm.lowercase(Locale.ROOT)
        val match = sites.filter { it.name.lowercase(Locale.ROOT).contains(s) }
        return match
    }}