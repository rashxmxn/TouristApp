package com.example.touristapp.adapter

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.touristapp.MainActivity
import com.example.touristapp.R
import com.example.touristapp.data.TempDataSourceObj
import com.example.touristapp.ui.wishlist.WishlistFragmentDirections

class WishlistAdapter(): RecyclerView.Adapter<WishlistAdapter.SiteCardViewHolder>() {

    var main = MainActivity()
    val wishlist = TempDataSourceObj.wishlist

    class SiteCardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val siteImageView : ImageView? = view?.findViewById(R.id.site_image)
        val siteNameText : TextView? = view?.findViewById(R.id.site_name)
        val siteDetailsButton: Button = view.findViewById<Button>(R.id.details_button)
        val siteWishlistButton: ImageButton = view.findViewById<ImageButton>(R.id.wishlist_button)
    }

    override fun getItemCount(): Int = wishlist.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteCardViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.site_item_view, parent, false)

//        layout.accessibilityDelegate = Accessibility

        return SiteCardViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WishlistAdapter.SiteCardViewHolder, position: Int) {
        val siteItem = wishlist[position]
        val context = holder.view.context

        holder.siteImageView?.setImageResource(siteItem.imageResourceId)
        holder.siteNameText?.text = siteItem.name

        if (wishlist.contains(siteItem)) {
            holder.siteWishlistButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        else {
            holder.siteWishlistButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        holder.siteDetailsButton.setOnClickListener {
//            val action = CityListFragmentDirections.actionCityListFragmentToSiteListFragment(city = cityItem.name)
//            val action = SiteListFragmentDirections.actionSiteListFragmentToSiteDetailsFragment(siteItem.name)
//            val action = SiteListFragmentDirections.actionSiteListFragmentToSiteDetailsFragment()
//            holder.view.findNavController().navigate(action)
        }

        holder.siteWishlistButton.setOnClickListener{
            holder.siteWishlistButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)

            val toast = Toast.makeText(context, "${siteItem.name} removed from Favorites", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
            toast.show()
            wishlist.remove(siteItem)
            scaler(holder.siteWishlistButton)
            holder.view.findNavController().navigate(WishlistFragmentDirections.actionNavigationWishlistSelf())
        }
    }

//    companion object Accessibility : View.AccessibilityDelegate() {
//        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//        override fun onInitializeAccessibilityNodeInfo(
//            host: View?,
//            info: AccessibilityNodeInfo?
//        ) {
//            super.onInitializeAccessibilityNodeInfo(host, info)
//            val customString = "Custom Accessibility"
//            val customClick =
//                AccessibilityNodeInfo.AccessibilityAction(
//                    AccessibilityNodeInfo.ACTION_CLICK,
//                    customString
//                )
//            info?.addAction(customClick)
//        }
//    }
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