package com.deannayee.caredriver.myrides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.deannayee.caredriver.databinding.FragmentRidesBinding


class RidesFragment : Fragment() {
    private lateinit var binding: FragmentRidesBinding

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRidesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}