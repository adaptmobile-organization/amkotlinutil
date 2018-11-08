package dk.adaptmobile.amkotlinutil.extensions

import androidx.viewpager.widget.ViewPager

/**
 * Created by bjarkeseverinsen on 11/08/2017.
 */

val ViewPager.length: Int?
    get() = adapter?.count

val ViewPager.lastIndex: Int?
    get() = adapter?.count?.minus(1)

val ViewPager.isLastView: Boolean
    get() = currentItem == length?.minus(1)

fun ViewPager.next() {
    if (!isLastView) {
        currentItem += 1
    }
}

fun ViewPager.next(lastCallback: () -> Unit) {
    if (!isLastView) {
        currentItem += 1
    } else {
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