package dk.adaptmobile.amkotlinutil.extensions

/**
 * Created by bjarkeseverinsen on 21/08/2017.
 */

fun CharSequence.isEmptyNullOrStringNull(): Boolean {
    return isNullOrEmpty() || this == "null"
}

