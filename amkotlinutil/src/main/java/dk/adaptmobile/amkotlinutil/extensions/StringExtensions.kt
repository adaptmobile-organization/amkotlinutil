package dk.adaptmobile.amkotlinutil.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun String.capitalizeFirstLetter(): String {
    return this.substring(0, 1).toUpperCase() + this.substring(1)
}

fun String.toDate(pattern: String): Date {
    return SimpleDateFormat(pattern, Locale("da", "DK")).parse(this)
}