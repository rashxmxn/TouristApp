package com.example.touristapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.touristapp.databinding.FragmentItineraryBinding
import com.example.touristapp.databinding.FragmentItineraryListItemBinding
import com.example.touristapp.model.Itinerary

//class ItineraryListAdapter {}

class ItineraryListAdapter(
    private val clickListener: (Itinerary) -> Unit
) : ListAdapter<Itinerary, ItineraryListAdapter.ItineraryViewHolder>(DiffCallback) {

    class ItineraryViewHolder(
        private var binding: FragmentItineraryListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(itinerary: Itinerary) {
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Itinerary>() {
        override fun areItemsTheSame(oldItem: Itinerary, newItem: Itinerary): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Itinerary, newItem: Itinerary): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItineraryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItineraryViewHolder(
            FragmentItineraryListItemBinding.inflate(layoutInflater, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ItineraryViewHolder, position: Int) {
        val itinerary = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(itinerary)
        }
        holder.bind(itinerary)
    }
}