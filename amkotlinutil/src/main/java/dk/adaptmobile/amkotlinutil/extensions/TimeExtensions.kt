package dk.adaptmobile.amkotlinutil.extensions

import java.util.concurrent.TimeUnit

/**
 * Created by bjarkeseverinsen on 27/03/2018.
 */

val Int.secondsInMillis: Long
    get() = TimeUnit.SECONDS.toMillis(this.toLong())

val Int.minutesInMillis: Long
    get() = TimeUnit.MINUTES.toMillis(this.toLong())

val Int.hoursInMillis: Long
    get() = TimeUnit.HOURS.toMillis(this.toLong())