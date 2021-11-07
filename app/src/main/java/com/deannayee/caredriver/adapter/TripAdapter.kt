package com.deannayee.caredriver.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deannayee.caredriver.databinding.TripViewBinding
import com.deannayee.caredriver.network.models.Trip

class TripAdapter(private val ridePos: Int, private val onItemClicked: (ridePosition: Int, tripPosition: Int) -> Unit):
                                ListAdapter<Trip, TripAdapter.TripViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem.displayDate() == newItem.displayDate()
        }

        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem == newItem
        }
    }

    class TripViewHolder(private var binding: TripViewBinding, private val ridePos: Int,
                         private val onItemClicked: (ridePosition: Int, tripPosition: Int) -> Unit):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(trip: Trip) {
            binding.trip = trip
            binding.tripCard.setOnClickListener(this)
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            val tripPosition = adapterPosition
            onItemClicked(ridePos, tripPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        return TripViewHolder(
            TripViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false), ridePos, onItemClicked)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val wayPoint = getItem(position)
        holder.bind(wayPoint)
    }
}