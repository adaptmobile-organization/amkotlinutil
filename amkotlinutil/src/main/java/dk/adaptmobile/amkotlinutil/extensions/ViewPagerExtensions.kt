package dk.adaptmobile.higgo.extensions

import android.support.v4.view.ViewPager

/**
 * Created by bjarkeseverinsen on 11/08/2017.
 */

fun ViewPager.isLastView(): Boolean {
    return currentItem == adapter.count - 1
}

fun ViewPager.next() {
    if (!isLastView()) {
        currentItem += 1
    }
}

fun ViewPager.nextCircular() {
    if (!isLastView()) {
        currentItem += 1
    } else {
        currentItem = 0
    }
}