package com.example.touristapp.ui.itinerary

import android.Manifest
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.touristapp.BaseApplication
import com.example.touristapp.MainActivity
import com.example.touristapp.R
import com.example.touristapp.databinding.FragmentAddItineraryBinding
import com.example.touristapp.model.Itinerary
import com.example.touristapp.ui.itinerary.viewmodel.ItineraryViewModel
import com.example.touristapp.ui.itinerary.viewmodel.ItineraryViewModelFactory
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class FragmentAddItinerary : Fragment() {

    private val navigationArgs: FragmentAddItineraryArgs by navArgs()

    private var _binding: FragmentAddItineraryBinding? = null

    private lateinit var itinerary: Itinerary

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>

    private val mainActivity: MainActivity
        get() {
            return MainActivity()
        }

    private val permission: String by lazy {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            Manifest.permission.READ_MEDIA_IMAGES } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
//        }
    }

    private val database: DatabaseReference by lazy {
        Firebase.database.reference.child("itineraries".uppercase())
    }

    private val storage: FirebaseStorage by lazy {
        Firebase.storage
    }

    private val storageRef: StorageReference by lazy {
        storage.reference.child("images").child(UUID.randomUUID().toString())
    }
    private var uri: Uri? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: ItineraryViewModel by activityViewModels {
        ItineraryViewModelFactory(
            (activity?.application as BaseApplication).database.itineraryDao(),
            requireActivity().application
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
            }
        }
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                this.uri = uri
                storageRef.putFile(uri).addOnSuccessListener {
                    Toast.makeText(requireContext(), "Photo uploaded!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItineraryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id > 0) {

            viewModel.getItinerary(id).observe(viewLifecycleOwner) { myitnerary ->
                myitnerary.let {
                    itinerary = myitnerary
                    bindItinerary(itinerary)
                }
            }

            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                scaler(binding.deleteBtn)
                deleteItinerary(itinerary)
                mainActivity.itemCount -= 1
            }
        } else {
            binding.saveBtn.setOnClickListener {
                scaler(binding.saveBtn)
                addItinerary()
                mainActivity.itemCount += 1
            }
        }

        binding.addPhoto.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) == PackageManager.PERMISSION_GRANTED -> {
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }

                shouldShowRequestPermissionRationale(
                    permission
                ) -> {
                }

                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(
                        permission
                    )
                }
            }

        }
    }

    private fun scaler(button: Button) {
        //scale animation
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(button, scaleX, scaleY)
        animator.repeatCount = 1
        animator.duration = 50
        animator.repeatMode = ObjectAnimator.REVERSE
        //animator.disableViewDuringAnimation(button)
        animator.start()
    }

    private fun deleteItinerary(itinerary: Itinerary) {
        viewModel.deleteItinerary(itinerary)
        findNavController().navigate(
            R.id.action_fragmentAddItinerary_to_navigation_itinerary
        )
    }

    private fun addItinerary() {
        if (isValidEntry()) {
            storageRef.downloadUrl.addOnSuccessListener {
                viewModel.addItinerary(
                    binding.nameInput.text.toString(),
                    binding.locationAddressInput.text.toString(),
                    false,
                    binding.dateInput.text.toString(),
                    binding.notesInput.text.toString(),
                    it.toString(),
                    database
                )
            }

            findNavController().navigate(
                R.id.action_fragmentAddItinerary_to_navigation_itinerary
            )
        }
    }

//    private fun updateItinerary() {
//        if (isValidEntry()) {
//            viewModel.updateItinerary(
//                id = navigationArgs.id,
//                name = binding.nameInput.text.toString(),
//                address = binding.locationAddressInput.text.toString(),
//                hasVisited = false,
//                date = binding.dateInput.text.toString(),
//                notes = binding.notesInput.text.toString()
//            )
//            findNavController().navigate(
//                R.id.action_fragmentAddItinerary_to_navigation_itinerary
//            )
//        }
//    }

    private fun bindItinerary(itinerary: Itinerary) {
        binding.apply {
            nameInput.setText(itinerary.name, TextView.BufferType.SPANNABLE)
            locationAddressInput.setText(itinerary.address, TextView.BufferType.SPANNABLE)
            dateInput.setText(itinerary.date, TextView.BufferType.SPANNABLE)
            notesInput.setText(itinerary.notes, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
//                updateItinerary()
            }
        }

    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.locationAddressInput.text.toString()
    )
}