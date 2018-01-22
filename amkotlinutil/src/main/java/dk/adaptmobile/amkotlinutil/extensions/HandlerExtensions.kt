package dk.adaptmobile.amkotlinutil.extensions

import android.os.Handler

/**
 * Created by bjarkeseverinsen on 21/01/2018.
 */

fun Handler.postDelayed(delayMillis: Long, function: () -> Unit) {
    this.postDelayed(function, delayMillis)
}