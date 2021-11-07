package com.deannayee.caredriver.network.models

import java.text.NumberFormat

open class HeaderInfo(val startDate: DateTime, val endDate: DateTime, var price: Double) {
    fun displayDate(): String{
        return startDate.formatDate()
    }

    fun displayTime(): String {
        val startTime = startDate.formatTime()
        val endTime = endDate.formatTime()
        return "$startTime - $endTime"
    }

    fun displayPrice(): String{
        return NumberFormat.getCurrencyInstance().format(price)
    }
}