package dk.adaptmobile.amkotlinutil.extensions

import android.os.Handler
import java.util.*

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

val Any.TAG: String
    get() = this::class.java.simpleName

fun danishLocale(): Locale {
    return Locale("da", "da_DK")
}

fun wait(delayMillis: Long, function: () -> Unit) {
    Handler().postDelayed(function, delayMillis)
}