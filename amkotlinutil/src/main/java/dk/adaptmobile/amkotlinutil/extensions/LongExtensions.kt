package dk.adaptmobile.amkotlinutil.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun Long.toDate(): Date {
    return Date(this)
}

fun Long.getDate(format: String = "dd-MM-yyyy"): String {
    return SimpleDateFormat().format(Date(this))
}

fun Long.getTime(format: String = "HH:mm"): String {
    return SimpleDateFormat(format).format(Date(this))
}
