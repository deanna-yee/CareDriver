package com.deannayee.caredriver.network.models

import java.text.NumberFormat

class Ride(start: DateTime, end: DateTime, val trips: MutableList<Trip>):
    HeaderInfo(start, end, 0.0){

    init{
        calculatePrice()
    }

    override fun equals(other: Any?): Boolean {
        if(other is Ride){
            return startDate == other.startDate && endDate == other.endDate && tripsSame(other.trips)
        }
        return false
    }

    private fun calculatePrice(){
        for(trip in trips){
            price += trip.price
        }
    }

    private fun tripsSame(other: List<Trip>): Boolean{
        if(trips.size != other.size){
            return false
        }
        for(i in other.indices){
            if(trips[i] != other[i]){
                return false
            }
        }
        return true
    }


}