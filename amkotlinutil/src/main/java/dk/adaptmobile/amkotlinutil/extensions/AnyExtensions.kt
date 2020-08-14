package dk.adaptmobile.amkotlinutil.extensions

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dk.adaptmobile.amkotlinutil.model.PostDelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
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

inline fun <reified T> Any.deepCopy(): T {
    val gson = GsonBuilder().create()
    val jsonString = gson.toJson(this)
    val type = object : TypeToken<T>() { }.type
    return gson.fromJson(jsonString, type)
}
