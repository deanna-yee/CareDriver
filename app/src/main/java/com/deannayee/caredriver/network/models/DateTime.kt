package com.deannayee.caredriver.network.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTime(dateTime: String){
    private val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val dateFormat = DateTimeFormatter.ofPattern("EE MM/dd")
    private val timeFormat = DateTimeFormatter.ofPattern("h:mm")
    private val amPmFormat = DateTimeFormatter.ofPattern("a")
    private val dateTime = LocalDateTime.parse(dateTime, dateTimeFormat)

    fun formatDate(): String{
        return dateTime.format(dateFormat)
    }

    fun formatTime(): String{
        val amPm = when(dateTime.format(amPmFormat)) {"AM" -> "a" else -> "p"}
        return "${dateTime.format(timeFormat)}$amPm"
    }
}