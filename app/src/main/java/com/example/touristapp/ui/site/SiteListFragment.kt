package com.example.touristapp.ui.site

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.touristapp.R
import com.example.touristapp.adapter.SiteAdapter
import com.example.touristapp.databinding.FragmentSiteListBinding

//class SiteListFragment: Fragment(R.layout.fragment_site_list){
//}

class SiteListFragment : Fragment() {

    companion object {
        val CITY = "city"
    }

    private var _binding: FragmentSiteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var cityID: String
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        arguments?.let {
            cityID = it.getString(CITY).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSiteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.layout_menu, menu)
//
//        val layoutButton = menu.findItem(R.id.action_switch_layout)
//        setIcon(layoutButton)
//    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        }
        recyclerView.adapter = SiteAdapter()
    }

//    private fun setIcon(menuItem: MenuItem?) {
//        if (menuItem == null)
//            return
//
//        menuItem.icon =
//            if (isLinearLayoutManager)
//                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
//            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_switch_layout -> {
//                // Sets isLinearLayoutManager (a Boolean) to the opposite value
//                isLinearLayoutManager = !isLinearLayoutManager
//                // Sets layout and icon
//                chooseLayout()
//                setIcon(item)
//
//                return true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}