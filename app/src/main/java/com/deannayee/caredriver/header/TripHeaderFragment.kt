package com.deannayee.caredriver.header

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.deannayee.caredriver.databinding.FragmentTripHeaderBinding

class TripHeaderFragment : Fragment() {
    private lateinit var binding: FragmentTripHeaderBinding
    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTripHeaderBinding.inflate(layoutInflater, container, false)
        return binding.root

    }
}