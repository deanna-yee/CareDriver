package com.deannayee.caredriver.header

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.deannayee.caredriver.R
import com.deannayee.caredriver.databinding.FragmentTripHeaderBinding
import com.deannayee.caredriver.detail.DetailFragment
import java.text.NumberFormat

class TripHeaderFragment : Fragment() {
    private lateinit var binding: FragmentTripHeaderBinding
    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTripHeaderBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(parentFragment is DetailFragment){
            setViewsForDetailView()
        }else{
            setViewsForRidesView()
        }
    }

    private fun setTextSize(textView: TextView, size:Float){
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }


    private fun setViewsForDetailView(){
        setTextSize(binding.startTimeText, 16F)
        setTextSize(binding.dash, 16F)
        setTextSize(binding.endTimeText, 16F)
        binding.priceText.setTextColor(ResourcesCompat.getColor(requireActivity().resources,
                                        R.color.white, null))
        binding.priceText.background = ResourcesCompat.getDrawable(requireActivity().resources,
                                        R.drawable.rounded_corners, null)
    }

    private fun setViewsForRidesView(){
        setTextSize(binding.startTimeText, 12F)
        setTextSize(binding.dash, 12F)
        setTextSize(binding.endTimeText, 12F)
        binding.priceText.setTextColor(ResourcesCompat.getColor(requireActivity().resources,
                R.color.midnight_blue, null))
        binding.priceText.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun setDateView(date: String){
        binding.dateText.text = date
    }

    private fun setTimeViews(startTime: String, endTime: String){
        binding.startTimeText.text = startTime
        binding.endTimeText.text = endTime
    }

    private fun setPriceView(price: Double){
        binding.priceText.text = NumberFormat.getCurrencyInstance().format(price)
    }

    fun setInfo(date: String, startTime: String, endTime: String, price: Double){
        setDateView(date)
        setTimeViews(startTime, endTime)
        setPriceView(price)
    }
}