package com.deannayee.caredriver.network.models

import com.squareup.moshi.Json

data class Location(
    val address: String,
    @Json(name = "lat") val latitude: Double,
    @Json(name = "lng") val longitude: Double
){
    override fun equals(other: Any?): Boolean {
        if(other is Location){
            return address == other.address && latitude == other.latitude &&
                    longitude == other.longitude
        }
        return false
    }
}