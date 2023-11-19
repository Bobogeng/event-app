package com.bangkit.eventapp.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.withDateFormat(): String {
    val iso8601Format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date = iso8601Format.parse(this) as Date

    return DateFormat.getDateInstance(DateFormat.FULL).format(date)
}