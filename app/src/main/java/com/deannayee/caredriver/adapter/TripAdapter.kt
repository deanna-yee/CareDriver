package com.deannayee.caredriver.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.deannayee.caredriver.header.TripHeaderFragment
import com.deannayee.caredriver.models.Trip

class TripAdapter(private val context: Context,
                  private val dataset: List<Trip>):
                    RecyclerView.Adapter<TripAdapter.TripViewHolder>(){
    class TripViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val header = view.findFragment<TripHeaderFragment>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}