package dk.adaptmobile.amkotlinutil.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun Date.resetHourMinSecForDate(): Date {
    val calendar = Calendar.getInstance(danishLocale())
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

fun Date.isDateSameDay(compareDate: Date): Boolean {
    return this.resetHourMinSecForDate() == compareDate.resetHourMinSecForDate()
}

fun Date.format(pattern: String, locale: Locale = danishLocale()): String {
    return SimpleDateFormat(pattern, locale).format(this)
}

fun Date.isDateThisYear(): Boolean {
    val calendar = Calendar.getInstance(danishLocale())

    val currentYear = calendar.get(Calendar.YEAR)
    calendar.time = this

    return currentYear == calendar.get(Calendar.YEAR)
}

fun Date.sameWeekLastYear(): Date {
    val calendar = Calendar.getInstance(danishLocale())
    calendar.time = this
    val currentWeek = calendar.get(Calendar.WEEK_OF_YEAR)
    val currentYear = calendar.get(Calendar.YEAR)
    calendar.set(Calendar.YEAR, currentYear - 1)
    calendar.set(Calendar.WEEK_OF_YEAR, currentWeek)
    return calendar.time
}

fun Date.lastDayOfMonth(): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH))
    return c.time
}

fun Date.lastDayOfWeek(): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_WEEK))
    return c.time
}

fun Date.isBetween(startDate: Date, endDate: Date): Boolean {
    return this.after(startDate) && this.before(endDate)
}

fun Date.toHoursAndMinutes(): String {
    val calendar =  Calendar.getInstance().apply {
        time = this@toHoursAndMinutes
    }

    return String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY),  calendar.get(Calendar.MINUTE))
}

fun Date.addHours(hours: Int) : Date {
    val validToCalendar: Calendar = Calendar.getInstance()
    validToCalendar.time = this
    validToCalendar.add(Calendar.HOUR, hours)
    return validToCalendar.time
}

fun Date.toHours(): String {
    return Calendar.getInstance().apply {
        time = this@toHours
    }.get(Calendar.HOUR_OF_DAY).toString()
}


