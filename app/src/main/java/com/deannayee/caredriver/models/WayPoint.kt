package com.deannayee.caredriver.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WayPoint(
    val id: Int,
    val anchor: Boolean,
    val passengers: List<Passenger>,
    val location: Location
)