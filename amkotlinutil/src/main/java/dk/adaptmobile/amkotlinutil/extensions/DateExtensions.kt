package dk.adaptmobile.amkotlinutil.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */


fun Date.resetHourMinSecForDate(): Date {
    val calendar = Calendar.getInstance(Locale("da", "DK"))
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}

fun Date.isDateToday(): Boolean {
    return this.resetHourMinSecForDate() == Date().resetHourMinSecForDate()
}

fun Date.compareDateSameDay(date2: Date): Boolean {
    return this.resetHourMinSecForDate() == date2.resetHourMinSecForDate()
}

fun Date.format(pattern: String): String {
    return SimpleDateFormat(pattern, Locale("da", "DK")).format(this)
}

fun Date.isDateThisYear(): Boolean {
    val calendar = Calendar.getInstance(Locale("da", "DK"))

    val currentYear = calendar.get(Calendar.YEAR)
    calendar.time = this

    return currentYear == calendar.get(Calendar.YEAR)
}