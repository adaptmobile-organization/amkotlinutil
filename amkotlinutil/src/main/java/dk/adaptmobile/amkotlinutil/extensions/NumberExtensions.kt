package dk.adaptmobile.amkotlinutil.extensions

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

/**
 * Returns Zero (0) if it is null
 */
fun Number?.orZero(): Number = if (this != null) this else 0

fun Int?.orZero(): Int = if (this != null) this else 0

fun Float?.orZero(): Float = if (this != null) this else 0.0f

fun Double?.orZero(): Double = if (this != null) this else 0.0