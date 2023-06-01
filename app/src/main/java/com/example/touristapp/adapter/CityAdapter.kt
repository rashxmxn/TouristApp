package com.example.touristapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.touristapp.R
import com.example.touristapp.data.TempDataSourceObj
import com.example.touristapp.model.City
import com.example.touristapp.ui.city.CityListFragmentDirections

class CityAdapter(private val dataset: List<City>): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private val cityList = TempDataSourceObj.cities.sortedBy { it.name?.toString() }

    class CityViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val cityNameText : TextView? = view?.findViewById(R.id.city_name)
        val cityButton: ImageButton = view.findViewById<ImageButton>(R.id.city_button)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.city_item_view, parent, false)

//        layout.accessibilityDelegate = Accessibility

        return CityViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val cityItem = cityList[position]
        val context = holder.view.context

        holder.cityNameText?.text = cityItem.name
        holder.cityButton?.setImageResource(cityItem.imageResourceId)

        holder.cityButton.setOnClickListener {
//            val action = CityListFragmentDirections.actionCityListFragmentToSiteListFragment(city = cityItem.name)
//            val action = CityListFragmentDirections.actionCityListFragmentToSiteListFragment(cityItem.name)
//            holder.view.findNavController().navigate(action)
        }
    }
//
//    companion object Accessibility :View.AccessibilityDelegate(){
//        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//         fun onInitializeAccessibilityNodeInfo(
//            host: View?,
//            info: AccessibilityNodeInfo?
//        ) {
//            super.onInitializeAccessibilityNodeInfo(host, info)
//            val customString = "Custom Text Accessibility"
//            val customClick =
//                AccessibilityNodeInfo.AccessibilityAction(
//                    AccessibilityNodeInfo.ACTION_CLICK,
//                    customString
//                )
//            info?.addAction(customClick)
//        }
//    }
}