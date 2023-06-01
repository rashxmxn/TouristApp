package com.example.touristapp.adapter

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.touristapp.R
import com.example.touristapp.data.TempDataSourceObj
import com.example.touristapp.model.Itinerary

class SiteAdapter :
    RecyclerView.Adapter<SiteAdapter.SiteCardViewHolder>() {

    var filteredSites: ArrayList<Itinerary> = arrayListOf()
        set(value) {
            notifyDataSetChanged()
            field = value
        }
    val wishlist = TempDataSourceObj.wishlist


    //if you edit, make sure to transfer edit to wishlist siteCardViewHolder and Search viewholder
    class SiteCardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val siteImageView: ImageView = view.findViewById(R.id.site_image)
        val siteNameText: TextView = view.findViewById(R.id.site_name)
        val siteDetailsButton: Button = view.findViewById<Button>(R.id.details_button)
        val siteWishlistButton: ImageButton = view.findViewById<ImageButton>(R.id.wishlist_button)
    }

    override fun getItemCount(): Int = filteredSites.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteCardViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.site_item_view, parent, false)


        return SiteCardViewHolder(layout)
    }

    override fun onBindViewHolder(holder: SiteCardViewHolder, position: Int) {
        val siteItem = filteredSites[position]
        val context = holder.view.context

        holder.siteNameText.text = siteItem.name
        Glide.with(holder.view.context).load(siteItem.image).into(holder.siteImageView)

//        if (wishlist.contains(siteItem)) {
//            holder.siteWishlistButton.setImageResource(R.drawable.ic_baseline_favorite_24)
//        } else {
//            holder.siteWishlistButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
//        }

        holder.siteDetailsButton.setOnClickListener {
//            val action = CityListFragmentDirections.actionCityListFragmentToSiteListFragment(city = cityItem.name)
            val bundle = bundleOf("item" to siteItem)
            holder.view.findNavController()
                .navigate(R.id.action_siteListFragment_to_siteDetailsFragment, bundle)
        }

        holder.siteWishlistButton.setOnClickListener {
            if (true) {
                holder.siteWishlistButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)

                val toast = Toast.makeText(
                    context,
                    "${siteItem.name} removed from Favorites",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
                scaler(holder.siteWishlistButton)

//                val toast = Toast.makeText(context, "${siteItem.name} already in Wishlist", Toast.LENGTH_SHORT)
//                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
//                toast.show()
            } else {
                holder.siteWishlistButton.setImageResource(R.drawable.ic_baseline_favorite_24)

                val toast = Toast.makeText(
                    context,
                    "${siteItem.name} added to Favorites",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
                scaler(holder.siteWishlistButton)
//                wishlist.add(siteItem)
            }
        }
    }

    private fun scaler(imageButton: ImageButton) {
        //scale animation
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(imageButton, scaleX, scaleY)
        animator.repeatCount = 1
        animator.duration = 50
        animator.repeatMode = ObjectAnimator.REVERSE
        //animator.disableViewDuringAnimation(button)
        animator.start()
    }
}