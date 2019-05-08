package dk.adaptmobile.amkotlinutil.extensions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

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

fun Double.format(pattern: String, groupingSeperator: Char): String {
    val symbols = DecimalFormatSymbols()
    symbols.groupingSeparator = groupingSeperator
    val df = DecimalFormat(pattern, symbols)
    return df.format(this)
}

fun Double.format(pattern: String, groupingSeperator: Char, decimalSeperator: Char): String {
    val symbols = DecimalFormatSymbols()
    symbols.decimalSeparator = decimalSeperator
    symbols.groupingSeparator = groupingSeperator
    val df = DecimalFormat(pattern, symbols)
    return df.format(this)
}

fun Double.roundDown(pattern: String): String {
    return format(pattern, RoundingMode.DOWN)
}

fun Double.roundHalfUp() = BigDecimal(this).setScale(0, BigDecimal.ROUND_HALF_UP).toDouble()

fun Double.roundToZero() = BigDecimal(this).setScale(0, BigDecimal.ROUND_DOWN).toDouble()