package dk.adaptmobile.amkotlinutil.extensions

import android.os.Handler
import dk.adaptmobile.amkotlinutil.model.PostDelay
import java.util.*

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

val Any.TAG: String
    get() = this::class.java.simpleName

fun danishLocale(): Locale {
    return Locale("da", "da_DK")
}

inline fun wait(delayMillis: Long, crossinline function: () -> Unit): PostDelay {
    val handler = Handler()
    val runnable = Runnable { function() }
    handler.postDelayed(runnable, delayMillis)
    return PostDelay(handler, runnable)
}