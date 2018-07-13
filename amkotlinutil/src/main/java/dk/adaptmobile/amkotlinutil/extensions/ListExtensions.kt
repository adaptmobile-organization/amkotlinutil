package dk.adaptmobile.amkotlinutil.extensions

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun <T> List<T>?.isLast(index: Int): Boolean {
    return index == this?.lastIndex
}

fun <T> List<T>?.sizeOrZero(): Int {
    return this?.size ?: 0
}