package dk.adaptmobile.amkotlinutil.extensions

import android.support.v4.view.ViewPager

/**
 * Created by bjarkeseverinsen on 11/08/2017.
 */

val ViewPager.length: Int
    get() = adapter.count

val ViewPager.isLastView: Boolean
    get() = currentItem == length - 1

fun ViewPager.next() {
    if (!isLastView) {
        currentItem += 1
    }
}

fun ViewPager.next(lastCallback: () -> Unit) {
    if (!isLastView) {
        currentItem += 1
    }else {
        lastCallback()
    }
}

fun ViewPager.nextCircular() {
    if (!isLastView) {
        currentItem += 1
    } else {
        currentItem = 0
    }
}