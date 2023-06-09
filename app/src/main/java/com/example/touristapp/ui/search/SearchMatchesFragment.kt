package com.example.touristapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.touristapp.R
import com.example.touristapp.data.TempDataSourceObj

class SearchMatchesFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchResultsDisplay: FrameLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterInUse: SimpleStringRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search_matches, container, false)

        searchResultsDisplay = root.findViewById(R.id.search_results_matches_frame_layout)
        recyclerView = root.findViewById(R.id.search_results_list)

        adapterInUse = SimpleStringRecyclerViewAdapter(root.context, listOf(""))
        adapterInUse.setViewModel(searchViewModel)
        with(recyclerView) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = adapterInUse
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (callback != null && dy > 0) {
                    callback!!.clearKeyboard()
                }
            }
        })
        return root
    }

    fun setViewModel(viewModel: SearchViewModel) {
        searchViewModel = viewModel
    }

    fun show() {
        searchResultsDisplay.visibility = View.VISIBLE
    }

    fun hide() {
        searchResultsDisplay.visibility = View.GONE
    }

    fun isShowing(): Boolean {
        return searchResultsDisplay.visibility == View.VISIBLE
    }

    fun startSearch(searchTerm: String) {
        val matches = TempDataSourceObj.searchSites(searchTerm)

        val matchNames = matches.map { it.name }
        adapterInUse.updateStringList(matchNames)
        adapterInUse.notifyDataSetChanged()
    }

    class SimpleStringRecyclerViewAdapter(context: Context, private val valuesIn: List<String>) :
        RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>() {

        private var stringListToDisplay = valuesIn
        private lateinit var searchViewModel: SearchViewModel

        class ViewHolder(val theView: View) : RecyclerView.ViewHolder(theView) {
            lateinit var boundString: String
            val textView: TextView = theView.findViewById(R.id.fragment_search_text)
                    as TextView
        }

        fun setViewModel(model: SearchViewModel) {
            searchViewModel = model
        }

        fun updateStringList(newList: List<String>) {
            stringListToDisplay = newList
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_search_item_textview, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.boundString = stringListToDisplay[position]
            holder.textView.text = stringListToDisplay[position]
            val context = holder.theView.context

            holder.textView.setOnClickListener {
                val siteName = holder.textView.text

                val action = SearchFragmentDirections.actionSearchFragmentToSiteDetailsFragment(
                    siteName as String
                )
                holder.theView.findNavController().navigate(action)
            }
        }

        override fun getItemCount(): Int {
            return stringListToDisplay.size
        }
    }

    fun setCallback(callbackIn: Callback) {
        callback = callbackIn
    }

    interface Callback {
        fun clearKeyboard()
    }

    private var callback: Callback? = null
}
