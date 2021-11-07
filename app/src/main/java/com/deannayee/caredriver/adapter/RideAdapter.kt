package com.deannayee.caredriver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.deannayee.caredriver.R
import com.deannayee.caredriver.databinding.RidesViewBinding
import com.deannayee.caredriver.dividers.SpaceDivider
import com.deannayee.caredriver.network.models.Ride

class RideAdapter(private val onItemClicked: (ridePosition: Int, tripPosition: Int) -> Unit): ListAdapter<Ride, RideAdapter.RideViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Ride>() {
        override fun areItemsTheSame(oldItem: Ride, newItem: Ride): Boolean {
            return oldItem.displayDate() == newItem.displayDate()
        }
        override fun areContentsTheSame(oldItem: Ride, newItem: Ride): Boolean {
            return oldItem == newItem
        }
    }

    class RideViewHolder(private var binding: RidesViewBinding, private val dpSize: Int,
                         private val onItemClicked: (ridePosition: Int, tripPosition: Int) -> Unit):
        RecyclerView.ViewHolder(binding.root){
        fun bind(ride: Ride?, position: Int) {
            binding.ride = ride
            binding.tripsList.adapter = TripAdapter(position){
                ridePosition, tripPosition ->  onItemClicked(ridePosition, tripPosition)
            }
            binding.tripsList.addItemDecoration(SpaceDivider(dpSize))
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val dpSize = parent.context.resources.getDimensionPixelSize(R.dimen.margin)
        return RideViewHolder(RidesViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false), dpSize, onItemClicked)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = getItem(position)
        holder.bind(ride, position)
    }
}

