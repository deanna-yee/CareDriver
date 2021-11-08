package com.deannayee.caredriver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deannayee.caredriver.databinding.WaypointViewBinding
import com.deannayee.caredriver.network.models.WayPoint

class WayPointAdapter: ListAdapter<WayPoint, WayPointAdapter.WayPointViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<WayPoint>() {
        override fun areItemsTheSame(oldItem: WayPoint, newItem: WayPoint): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WayPoint, newItem: WayPoint): Boolean {
            return oldItem == newItem
        }

    }

    class WayPointViewHolder(private var binding: WaypointViewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(wayPoint: WayPoint, position: Int, itemCount: Int) {
            binding.wayPoint = wayPoint
            binding.waypointType = if(position == itemCount - 1){"Drop-off"} else {"Pickup"}
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WayPointViewHolder {
        return WayPointViewHolder(
            WaypointViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WayPointViewHolder, position: Int) {
        val ride = getItem(position)

        holder.bind(ride, position, itemCount)
    }
}