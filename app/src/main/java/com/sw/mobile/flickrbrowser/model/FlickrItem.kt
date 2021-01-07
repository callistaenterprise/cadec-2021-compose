package com.sw.mobile.flickrbrowser.model

import android.icu.text.SimpleDateFormat
import java.util.*

fun getDate(myDate: String): String {
    var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    val date = dateFormat.parse(myDate!!)
    dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
    return dateFormat.format(date!!)
}
data class FlickrItem(val id: String, val title: String, val date: String, val imageUrl: String ){
    val formattedDate: String
        get() = if(this.date != null ) getDate(this.date) else "-"
}

