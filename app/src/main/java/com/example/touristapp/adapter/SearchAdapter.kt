package com.example.touristapp.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.touristapp.R
import com.example.touristapp.data.TempDataSourceObj
import com.example.touristapp.model.Site

class SearchAdapter(private val dataset: List<Site>) : RecyclerView.Adapter<SearchAdapter.SiteCardViewHolder>() {

    private val siteList = TempDataSourceObj.cities
    private val filteredSites: List<Site>
    init {
        filteredSites = TempDataSourceObj.sites
//        filteredSites = sites.filter { it.city.equals(cityID) }
    }
    val watchlist = TempDataSourceObj.wishlist

    //if you edit, make sure to transfer edit to wishlist siteCardViewHolder and Search viewholder
    class SiteCardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val siteImageView : ImageView? = view?.findViewById(R.id.site_image)
        val siteNameText : TextView? = view?.findViewById(R.id.site_name)
        val siteDetailsButton: Button = view.findViewById<Button>(R.id.details_button)
        val siteWishlistButton: Button = view.findViewById<Button>(R.id.wishlist_button)
    }

    override fun getItemCount(): Int = filteredSites.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteCardViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.site_item_view, parent, false)

//        layout.accessibilityDelegate = Accessibility

        return SiteCardViewHolder(layout)
    }

    override fun onBindViewHolder(holder: SiteCardViewHolder, position: Int) {
        val siteItem = filteredSites[position]
        val context = holder.view.context

        holder.siteNameText?.text = siteItem.name
        holder.siteImageView?.setImageResource(siteItem.imageResourceId)

        //FOR LINKS
//        val imdbLink = movieItem.imdb
//        holder.movieDetailsButton.setOnClickListener {
//            val queryUrl: Uri = Uri.parse(imdbLink)
//            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
//            context.startActivity(intent)
//        }

        holder.siteDetailsButton.setOnClickListener {
//            val action = CityListFragmentDirections.actionCityListFragmentToSiteListFragment(city = cityItem.name)
//            val action = SiteListFragmentDirections.actionSiteListFragmentToSiteDetailsFragment(siteItem.name)
//            val action = SiteListFragmentDirections.actionSiteListFragmentToSiteDetailsFragment()
//            holder.view.findNavController().navigate(action)
        }

        holder.siteWishlistButton.setOnClickListener{
            if (watchlist.contains(siteItem)) {
                val toast = Toast.makeText(context, "${siteItem.name} already in Wishlist", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
            }
            else {
                val toast = Toast.makeText(context, "${siteItem.name} added to Wishlist", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
                watchlist.add(siteItem)
            }
        }
    }

//    companion object Accessibility : View.AccessibilityDelegate() {
//        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//        override fun onInitializeAccessibilityNodeInfo(
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