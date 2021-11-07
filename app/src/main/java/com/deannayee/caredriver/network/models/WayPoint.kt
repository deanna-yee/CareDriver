package com.deannayee.caredriver.network.models

class WayPoint(
    val id: Int,
    val anchor: Boolean,
    val passengers: List<Passenger>,
    val location: Location
){
    var riders: Int = 0
        private set
    var boosters: Int = 0
        private set


    init{
        calculateRiders()
    }

    override fun equals(other: Any?): Boolean {
        if(other is WayPoint){
            return anchor == other.anchor && location == other.location &&
                    passengersEqual(other.passengers)
        }
        return false
    }

    private fun passengersEqual(other: List<Passenger>): Boolean{
        if(passengers.size != other.size) {
            return false
        }
        for(i in other.indices){
            if(passengers[i] != other[i]){
                return false
            }
        }
        return true
    }

    private fun calculateRiders() {
        for(passenger in passengers){
            if(passenger.boosterSeat){
                boosters++
            }else{
                riders++
            }
        }
    }
}