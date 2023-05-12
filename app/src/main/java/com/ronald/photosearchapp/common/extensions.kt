package com.ronald.photosearchapp.common

import java.text.SimpleDateFormat
import java.util.Date

fun String.getDateTime(): String {
    return try {
        val formatDate = SimpleDateFormat("MMM dd yyyy")
        val dateFormated = Date(this.toLong() * 1000)
        formatDate.format(dateFormated)
    } catch (e: Exception) {
        ""
    }
}