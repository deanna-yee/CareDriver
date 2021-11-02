package com.deannayee.caredriver.models

import com.squareup.moshi.Json

data class Location(
    val address: String,
    @Json(name = "lat") val latitude: Double,
    @Json(name = "lng") val longitude: Double
)