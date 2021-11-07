package com.deannayee.caredriver.network.models
import com.squareup.moshi.Json
import java.text.NumberFormat


class Trip(
    @Json(name = "trip_id") val id: Int,
    @Json(name = "in_series") val inSeries: Boolean,
    @Json(name = "starts_at") val startsAt: String,
    @Json(name = "ends_at") val endsAt: String,
    @Json(name = "estimated_earnings_cents") val earnings: Int,
    @Json(name = "estimated_ride_minutes") val minutes: Int,
    @Json(name = "estimated_ride_miles") val miles: Double,
    @Json(name = "ordered_waypoints") val wayPoints: List<WayPoint>
): HeaderInfo(DateTime(startsAt), DateTime(endsAt), earnings/ 100.0){
    var riders: Int = 0
        private set
    var boosters: Int = 0
        private set

    init {
        calculateRiders()
    }

    fun displayWayPoints(): String{
        val wayPointsBuilder = StringBuilder()
        for(i in wayPoints.indices){
            if(i < wayPoints.size - 1) {
                wayPointsBuilder.append("${i + 1}. ${wayPoints[i].location.address}\n")
            }else{
                wayPointsBuilder.append("${i + 1}. ${wayPoints[i].location.address}")
            }
        }
        return wayPointsBuilder.toString()
    }



    fun calculateRiders(){
        for(wayPoint in wayPoints){
            riders += wayPoint.riders
            boosters += wayPoint.boosters
        }
    }

    override fun equals(other: Any?): Boolean {
        if(other is Trip) {
            return inSeries == other.inSeries && startsAt == other.startsAt &&
                    endsAt == other.endsAt && earnings == other.earnings &&
                    minutes == other.minutes && miles == other.miles &&
                    wayPointsSame(other.wayPoints)
        }
        return false
    }

    private fun wayPointsSame(other: List<WayPoint>): Boolean{
        if(wayPoints.size != other.size) {
            return false
        }
        for(i in other.indices){
            if(wayPoints[i] != other[i]){
                return false
            }
        }
        return true
    }
}