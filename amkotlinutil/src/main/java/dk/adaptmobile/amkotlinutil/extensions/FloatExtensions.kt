package dk.adaptmobile.amkotlinutil.extensions

import java.math.BigDecimal

fun Float.roundUpToNearestHalf(): Float {
    val value = this.toDouble()
    val decimal = BigDecimal.valueOf(value).remainder(BigDecimal.ONE).toFloat()

    // If the value is above x.5, then we want to round it up. If it is below, then we always want to round up to x.5
    return if (decimal >= 0.5f) {
        Math.ceil(value).toFloat()
    } else {
        (Math.floor(value) + 0.5).toFloat()
    }
}

fun Float.roundDownToNearestHalf(): Float {
    val value = this.toDouble()

    if (value >= 0) {
        val decimal = BigDecimal.valueOf(value).remainder(BigDecimal.ONE).toFloat()

        // If the value is below x.5, then we want to round it down. If it is above, then we always want to round up to x.5
        return if (decimal <= 0.5f) {
            Math.floor(value).toFloat()
        } else {
            (Math.floor(value) + 0.5).toFloat()
        }
    } else {
        val decimal = BigDecimal.valueOf(value).remainder(BigDecimal.ONE).toFloat()

        // If the value is below x.5, then we want to round it down. If it is above, then we always want to round up to x.5
        return if (decimal >= 0.5f) {
            Math.floor(value).toFloat()
        } else {
            (Math.floor(value) + 0.5).toFloat()
        }
    }
}