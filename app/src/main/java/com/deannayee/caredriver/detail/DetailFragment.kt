package com.deannayee.caredriver.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.deannayee.caredriver.R
import com.deannayee.caredriver.adapter.WayPointAdapter
import com.deannayee.caredriver.databinding.FragmentDetailBinding
import com.deannayee.caredriver.dividers.DividerTop
import com.deannayee.caredriver.myrides.RidesViewModel
import com.deannayee.caredriver.network.models.Trip
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*


class DetailFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentDetailBinding
    private var trip: Trip? = null
    private val viewModel: RidesViewModel by activityViewModels()
    private var ridePosition: Int = 0
    private var tripPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        ridePosition = arguments?.getInt("ridePosition")!!
        tripPosition = arguments?.getInt("tripPosition")!!
        trip = viewModel.rides.value?.get(ridePosition)?.trips?.get(tripPosition)
        binding.trip = trip
        binding.addressList.adapter = WayPointAdapter()

        binding.lifecycleOwner = activity

        context?.let {
            binding.headerView.priceText.apply{
                background = ContextCompat.getDrawable(it, R.drawable.rounded_corners)
                binding.headerView.priceText.setTextColor(ContextCompat.getColor(it, R.color.white))
            }
            binding.headerView.timeText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            val divider = AppCompatResources.getDrawable(it, R.drawable.divider)
            divider?.let {
                binding.addressList.addItemDecoration(
                    DividerTop(divider)
                )
                binding.addressList.addItemDecoration(
                    DividerItemDecoration(context, LinearLayout.VERTICAL)
                        .apply{setDrawable(divider)})
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancelButton.setOnClickListener {
            viewModel.cancelTrip(ridePosition, tripPosition)
            val cancel = DetailFragmentDirections.cancelTrip()
            findNavController().navigate(cancel)
        }
        binding.mapView.let {
            mapView ->
            mapView.getMapAsync(this)
            mapView.onCreate(savedInstanceState)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val waypoints = trip?.wayPoints
        waypoints?.let{
            val start = waypoints[0].location
            val end = waypoints[waypoints.size - 1].location
            val startLoc = LatLng(start.latitude, start.longitude)
            val endLoc = LatLng(end.latitude, end.longitude)
            createWayMarker(googleMap, startLoc, R.drawable.start_way_marker)
            createWayMarker(googleMap, endLoc, R.drawable.end_way_marker)
            val bound = buildBounds(startLoc, endLoc)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bound, 100))
        }
    }

    private fun buildBounds(start: LatLng, end: LatLng): LatLngBounds{
        val builder = LatLngBounds.builder()
        builder.run {
            include(start)
            include(end)
        }
        return builder.build()
    }

    private fun createWayMarker(googleMap: GoogleMap, location: LatLng, resourceId: Int){
        val bitmap = convertDrawableToBitmap(resourceId)
        bitmap?.let {
            googleMap.addMarker(
                MarkerOptions()
                    .position(location)
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    .flat(true)
            )
        }
    }

    private fun convertDrawableToBitmap(resourceId: Int): Bitmap? {
        val draw = ResourcesCompat.getDrawable(resources, resourceId, null)
        val bitmap = draw?.let {
            draw.toBitmap(draw.intrinsicWidth, draw.intrinsicHeight)
        }
        return bitmap
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
}