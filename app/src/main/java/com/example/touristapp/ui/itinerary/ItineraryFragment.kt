package com.example.touristapp.ui.itinerary

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.database.Cursor
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.GravityInt
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.touristapp.BaseApplication
import com.example.touristapp.MainActivity
import com.example.touristapp.R
import com.example.touristapp.adapter.ItineraryListAdapter
import com.example.touristapp.data.ItineraryDao
import com.example.touristapp.databinding.ActivityMainBinding
import com.example.touristapp.databinding.FragmentItineraryBinding
import com.example.touristapp.ui.itinerary.viewmodel.ItineraryViewModel
import com.example.touristapp.ui.itinerary.viewmodel.ItineraryViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

//class ItineraryFragment:Fragment(R.layout.fragment_itinerary) {}

class ItineraryFragment : Fragment() {

    private val viewModel: ItineraryViewModel by activityViewModels {
        ItineraryViewModelFactory(
            (activity?.application as BaseApplication).database.itineraryDao(),
            requireActivity().application
        )
    }

    private var _binding: FragmentItineraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mainActivity: MainActivity
        get() {
            return MainActivity()
        }

    private val myRecyclerView: RecyclerView
        get() {
            return binding.recyclerView
        }
    private val myEmptyView: TextView
        get() {
            return binding.emptyView
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItineraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItineraryListAdapter { itinerary ->
            val action = ItineraryFragmentDirections
                .actionNavigationItineraryToFragmentItineraryDetails(itinerary.id)
            findNavController().navigate(action)
        }

        viewModel.allItineraries.observe(viewLifecycleOwner) { itineraries ->
            itineraries.let { adapter.submitList(it) }
        }

        binding.apply {
            recyclerView.adapter = adapter
            addItineraryFab.setOnClickListener {
                scaler(addItineraryFab)
                findNavController().navigate(
                    R.id.action_navigation_itinerary_to_fragmentAddItinerary
                )
            }
        }
        val toast = Toast.makeText(context, "Click on + Icon to Add Itinerary", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show()
        //myEmptyView.text = mainActivity.itemCount.toString()
        //myEmptyView.setVisibility(View.VISIBLE)

//        if (myRecyclerView.isEmpty()) {
//            //recyclerView.setVisibility(View.GONE);
//            myEmptyView.setVisibility(View.VISIBLE);
//        }
//        else {
//            //recyclerView.setVisibility(View.VISIBLE);
//            myEmptyView.setVisibility(View.GONE);
//        }
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
}