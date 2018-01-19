package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.View

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

/**
 * Hides all the views passed in the arguments
 */
fun Context.hideViews(vararg views: View) = views.forEach { it.visibility = View.GONE }

/**
 * Shows all the views passed in the arguments
 */
fun Context.showViews(vararg views: View) = views.forEach { it.visibility = View.VISIBLE }


fun Context.getColorCompat(resId: Int): Int = ContextCompat.getColor(this, resId)

fun Context.getDrawableCompat(resId: Int): Drawable = ContextCompat.getDrawable(this, resId)

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Context.getDimension(resId: Int): Float = this.resources.getDimension(resId)