package com.example.touristapp.ui.site

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.touristapp.R
import com.example.touristapp.data.TempDataSourceObj
import com.example.touristapp.databinding.FragmentItineraryDetailsBinding
import com.example.touristapp.databinding.FragmentSiteDetailsBinding
import com.example.touristapp.databinding.FragmentSiteListBinding
import com.example.touristapp.model.Itinerary
import com.example.touristapp.model.Site
import com.example.touristapp.ui.itinerary.FragmentItineraryDetailsDirections

class SiteDetailsFragment : Fragment() {

    private val args: SiteDetailsFragmentArgs by navArgs()

    private var _binding: FragmentSiteDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var siteID: String

    private val item: Itinerary? by lazy {
        arguments?.getParcelable("item")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentSiteDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            item?.let {
                Glide.with(this@SiteDetailsFragment).load(it.image).into(siteImage)
                siteName.text = it.name
                siteDetails.text =
                    it.notes + "\n" + "address:${it.address}" + "\n" + "date:${it.date}"
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}