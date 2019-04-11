package dk.adaptmobile.amkotlinutil.extensions

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import dk.adaptmobile.amkotlinutil.model.PostDelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

val Any.TAG: String
    get() = this::class.java.simpleName

fun danishLocale(): Locale {
    return Locale("da", "da_DK")
}

inline fun wait(delayMillis: Long, mainThread: Boolean = false, crossinline function: () -> Unit): PostDelay {
    val handler = when (mainThread) {
        true -> Handler(Looper.getMainLooper())
        false -> Handler()
    }
    val runnable = Runnable { function() }
    handler.postDelayed(runnable, delayMillis)
    return PostDelay(handler, runnable)
}

@SuppressLint("CheckResult")
inline fun waitRx(delayMillis: Long, crossinline function: () -> Unit) {
    Observable.timer(delayMillis, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                function()
            }
}



