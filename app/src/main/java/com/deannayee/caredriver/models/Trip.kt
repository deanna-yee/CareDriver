package com.deannayee.caredriver.models


import com.deannayee.caredriver.models.DateTime
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



@JsonClass(generateAdapter = true)
data class Trip(
    @Json(name = "trip_id") val id: Int,
    @Json(name = "in_series") val inSeries: Boolean,
    @Json(name = "starts_at") val startsAt: String,
    @Json(name = "ends_at") val endsAt: String,
    @Json(name = "estimated_earnings_cents") val earnings: Int,
    @Json(name = "estimated_ride_minutes") val minutes: Int,
    @Json(name = "estimated_ride_miles") val miles: Double,
    @Json(name = "ordered_waypoints") val wayPoints: List<WayPoint>
){
    private val startDate: DateTime = DateTime(startsAt)
    private val endDate: DateTime = DateTime(endsAt)

    fun displayDate(): String{
        return startDate.formatDate()
    }

    fun displayStartTime(): String{
        return startDate.formatTime()
    }

    fun displayEndTime(): String{
        return endDate.formatTime()
    }
}