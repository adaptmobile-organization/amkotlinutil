package dk.adaptmobile.amkotlinutil.extensions

import java.text.DecimalFormat

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun Double.format(pattern: String): String {
    val df = DecimalFormat(pattern)
    return df.format(this)
}