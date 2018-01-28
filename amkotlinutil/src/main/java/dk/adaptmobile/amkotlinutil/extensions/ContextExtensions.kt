package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.TypedValue
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

fun Context.convertSpToPixels(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
}

fun Context.convertSpToPixels(redId: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.getDimension(redId), resources.displayMetrics)
}

fun Context.convertDpToPixels(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
}

fun Context.convertDpToPixels(redId: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.getDimension(redId), resources.displayMetrics)
}