package dk.adaptmobile.amkotlinutil.extensions

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun Double.format(pattern: String): String {
    val df = DecimalFormat(pattern)
    return df.format(this)
}

fun Double.format(pattern: String, init: DecimalFormat.() -> Unit): String {
    val df = DecimalFormat(pattern)
    df.init()
    return df.format(this)
}

fun Double.format(pattern: String, roundingMode: RoundingMode): String {
    return format(pattern) {
        this.roundingMode = roundingMode
    }
}

fun Double.roundDown(pattern: String): String {
    return format(pattern, RoundingMode.DOWN)
}

