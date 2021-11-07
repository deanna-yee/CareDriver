package com.deannayee.caredriver.adapter

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deannayee.caredriver.R
import com.deannayee.caredriver.myrides.TripApiStatus
import com.deannayee.caredriver.network.models.Ride
import com.deannayee.caredriver.network.models.Trip
import com.deannayee.caredriver.network.models.WayPoint

@BindingAdapter("tripData")
fun bindTripsRecylerView(recyclerView: RecyclerView, data: List<Trip>){
    val adapter = recyclerView.adapter as TripAdapter
    adapter.submitList(data)
}

@BindingAdapter("waypointData")
fun bindWaypointRecylerView(recyclerView: RecyclerView, data: List<WayPoint>){
    val adapter = recyclerView.adapter as WayPointAdapter
    adapter.submitList(data)
}

@BindingAdapter("timeSet")
fun bindTime(timeText: TextView, time: String){
    val timeBuilder = SpannableString(time)
    val firstSpace = time.indexOf(' ', 0)
    timeBuilder.setSpan(
        StyleSpan(Typeface.BOLD), 0, firstSpace,
        Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    timeText.setText(timeBuilder, TextView.BufferType.SPANNABLE)

}

@BindingAdapter("summary")
fun bindSummary(textView: TextView, trip: Trip){
    val context = textView.context
    val miles = context.getString(R.string.miles, trip.miles)
    val minutes = context.resources.getQuantityString(R.plurals.minutes, trip.minutes, trip.minutes)
    val summary = context.getString(R.string.summary, trip.id, miles, minutes)
    textView.text = summary
}

@BindingAdapter("inSeries")
fun bindSeries(textView: TextView, inSeries: Boolean){
    when(inSeries){
        true -> textView.visibility = View.VISIBLE
        false -> textView.visibility = View.GONE
    }
}


@BindingAdapter("isAnchored")
fun bindAnchorImage(imageView: ImageView, anchor: Boolean){
    val context = imageView.context
    val image = when(anchor){
        true -> ContextCompat.getDrawable(context, R.drawable.anchor)
        false -> ContextCompat.getDrawable(context, R.drawable.not_anchor)
    }
    imageView.setImageDrawable(image)
}


@BindingAdapter("tripApiStatusError")
fun bindStatus(statusImageView: ImageView, status: TripApiStatus?) {
    when (status) {
        TripApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("tripApiStatus")
fun bindStatus(statusProgress: ProgressBar, status: TripApiStatus?) {
    when (status) {
        TripApiStatus.LOADING -> {
            statusProgress.visibility = View.VISIBLE
        }
        else -> {
            statusProgress.visibility = View.GONE
        }
    }
}

@BindingAdapter("ridersSet")
fun bindRiders(textView: TextView, trip: Trip){
    val context = textView.context
    val riders = when(trip.riders){
       0 -> ""
       else -> context.resources.getQuantityString(R.plurals.rider, trip.riders, trip.riders)
    }
    val boosters = when(trip.boosters){
        0 -> ""
        else -> context.resources.getQuantityString(R.plurals.booster, trip.boosters, trip.boosters)
    }
    var ridersString = ""
    if(trip.riders > 0 && trip.boosters == 0){
        ridersString = context.getString(R.string.riders_info,riders)
    }
    else if(trip.riders == 0 && trip.boosters > 0){
        ridersString = context.getString(R.string.boosters_info, boosters)
    }else if(trip.riders > 0 && trip.boosters > 0){
        ridersString = context.getString(R.string.riders_booster_info, riders, boosters)
    }
    textView.text = ridersString
}


