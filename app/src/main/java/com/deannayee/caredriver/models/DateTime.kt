package com.deannayee.caredriver.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTime(dateTime: String){
    private val dateFormat:DateTimeFormatter = DateTimeFormatter.ofPattern("EE MM/dd")
    private val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mma")
    private val dateTime:LocalDateTime = LocalDateTime.parse(dateTime)

    fun formatDate(): String{
        return dateTime.format(dateFormat)
    }

    fun formatTime(): String{
        return dateTime.format(timeFormat)
    }


}