package dk.adaptmobile.amkotlinutil.extensions

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

fun Boolean?.orFalse(): Boolean = if (this != null) this else false