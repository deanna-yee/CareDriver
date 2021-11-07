package com.deannayee.caredriver.myrides

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.deannayee.caredriver.R
import com.deannayee.caredriver.adapter.RideAdapter
import com.deannayee.caredriver.databinding.FragmentRidesBinding
import com.deannayee.caredriver.dividers.DividerExcludeLast
import com.deannayee.caredriver.dividers.DividerTop


class RidesFragment : Fragment() {
    private val viewModel: RidesViewModel by activityViewModels()
    private lateinit var binding: FragmentRidesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRidesBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = activity
        binding.ridesList.adapter = RideAdapter{
                ridePosition, tripPosition -> onTripClicked(ridePosition, tripPosition)
        }
        context?.let {
            val divider = AppCompatResources.getDrawable(it, R.drawable.divider)
            divider?.let {
                binding.ridesList.addItemDecoration(
                    DividerExcludeLast(divider)
                )
            }
        }
        viewModel.rides.observe(this, Observer{
            (binding.ridesList.adapter as? RideAdapter)?.submitList(viewModel.rides.value)
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.ridesList.adapter = null
    }

    private fun onTripClicked(ridesPosition: Int, tripPosition:Int ){
        val location = RidesFragmentDirections.actionRidesFragmentToDetailFragment(ridesPosition,
                tripPosition)
        findNavController().navigate(location)
    }
}