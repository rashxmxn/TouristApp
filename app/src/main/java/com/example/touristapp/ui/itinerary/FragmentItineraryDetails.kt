package com.example.touristapp.ui.itinerary

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.touristapp.BaseApplication
import com.example.touristapp.R
import com.example.touristapp.databinding.FragmentItineraryDetailsBinding
import com.example.touristapp.model.Itinerary
import com.example.touristapp.ui.itinerary.viewmodel.ItineraryViewModel
import com.example.touristapp.ui.itinerary.viewmodel.ItineraryViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.reflect.KProperty

class FragmentItineraryDetails : Fragment() {

    private val navigationArgs: FragmentItineraryDetailsArgs by navArgs()

    private val viewModel: ItineraryViewModel by activityViewModels {
        ItineraryViewModelFactory(
            (activity?.application as BaseApplication).database.itineraryDao(),
            requireActivity().application
        )
    }

    private lateinit var itinerary: Itinerary

    private var _binding: FragmentItineraryDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItineraryDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        viewModel.getItinerary(id).observe(viewLifecycleOwner) { myitinerary ->
            myitinerary.let {
                itinerary = myitinerary
                bindForageable()
            }
        }

    }

    private fun bindForageable() {
        binding.apply {
            placeName.text = itinerary.name
            location.text = itinerary.address
            date.text = itinerary.date
            notes.text = itinerary.notes
            if (itinerary.hasVisited) {
                hasVisited.text = getString(R.string.has_visited)
            } else {
                hasVisited.text = getString(R.string.not_visited)
            }
            editItineraryFab.setOnClickListener {
                scaler(editItineraryFab)
                val action = FragmentItineraryDetailsDirections
                    .actionFragmentItineraryDetailsToFragmentAddItinerary(itinerary.id)
                findNavController().navigate(action)
            }

            location.setOnClickListener {
                launchMap()
            }
        }
    }

    private fun scaler(floatingActionButton: FloatingActionButton) {
        //scale animation
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(floatingActionButton, scaleX, scaleY)
        animator.repeatCount = 1
        animator.duration = 50
        animator.repeatMode = ObjectAnimator.REVERSE
        //animator.disableViewDuringAnimation(button)
        animator.start()
    }

    private fun launchMap() {
        val address = itinerary.address.let {
            it.replace(", ", ",")
            it.replace(". ", " ")
            it.replace(" ", "+")
        }
        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}
