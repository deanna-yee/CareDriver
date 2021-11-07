package com.deannayee.caredriver.network.models

import com.squareup.moshi.Json

data class Passenger(
    val id: Int,
    @Json(name = "booster_seat") val boosterSeat: Boolean,
    @Json(name = "first_name") val firstName: String
){
    override fun equals(other: Any?): Boolean {
        if(other is Passenger) {
            return boosterSeat == other.boosterSeat && firstName == other.firstName
        }
        return false
    }
}