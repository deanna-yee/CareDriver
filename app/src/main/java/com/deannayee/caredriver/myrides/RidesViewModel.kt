package com.deannayee.caredriver.myrides

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deannayee.caredriver.network.RidesApi
import com.deannayee.caredriver.network.models.DateTime
import com.deannayee.caredriver.network.models.Ride
import com.deannayee.caredriver.network.models.Trip
import kotlinx.coroutines.launch

enum class TripApiStatus{LOADING, ERROR, DONE}

class RidesViewModel: ViewModel() {
    private val _rides = MutableLiveData<MutableList<Ride>>()
    val rides: LiveData<MutableList<Ride>> = _rides

    private val _status = MutableLiveData<TripApiStatus>()
    val status: LiveData<TripApiStatus> = _status

    init{
        _rides.value = mutableListOf()
        getTrips()
    }

    private fun addRide(start: DateTime, end: DateTime, rides: MutableList<Ride>, dateTrips: MutableList<Trip>){
        val ride = Ride(start, end, dateTrips)
        rides.add(ride)
    }

    fun cancelTrip(ridePosition: Int, tripPosition: Int){
        _rides.value?.get(ridePosition)?.trips?.removeAt(tripPosition)
        updateRide(ridePosition)
    }

    private fun updateRide(ridePosition: Int){
        val ride = _rides.value?.get(ridePosition)
        ride?.let{
            if(ride.trips.size > 0) {
                val newRide = Ride(ride.trips[0].startDate,
                                ride.trips[ride.trips.size - 1].endDate, ride.trips)
                _rides.value?.set(ridePosition, newRide)
            }else{
                _rides.value?.removeAt(ridePosition)
            }
        }
    }

    private fun createRides(trips: List<Trip>){
        val rides = mutableListOf<Ride>()
        var dateTrips = mutableListOf<Trip>()
        var prevTrip:Trip? = null
        var start: DateTime =  trips[0].startDate
        for(trip in trips){
            if(prevTrip == null || prevTrip.displayDate() == trip.displayDate()){
                dateTrips.add(trip)
            }else{
                addRide(start, prevTrip.endDate, rides, dateTrips)
                start = trip.startDate
                dateTrips = mutableListOf(trip)
            }
            prevTrip = trip
        }
        if(prevTrip != null) {
            addRide(start, prevTrip.endDate, rides, dateTrips)
        }
        _rides.value = rides
    }

    private fun getTrips(){
        viewModelScope.launch{
            _status.value = TripApiStatus.LOADING
            try {
                val rides = RidesApi.retrofitService.getRides().rides
                createRides(rides)
                _status.value = TripApiStatus.DONE
            }catch(e: Exception){
                Log.e("ViewModel", e.message!!)
                _status.value = TripApiStatus.ERROR
                _rides.value = mutableListOf()
            }
        }
    }
}