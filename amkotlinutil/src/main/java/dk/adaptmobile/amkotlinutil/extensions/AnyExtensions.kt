package dk.adaptmobile.amkotlinutil.extensions

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

val Any.TAG: String
    get() = this::class.java.simpleName
