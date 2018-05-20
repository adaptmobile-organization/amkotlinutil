package dk.adaptmobile.amkotlinutil.extensions

import android.content.res.Resources
import android.graphics.Point
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.LinearInterpolator

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

/**
 * Fades in the View
 */
fun View.fadeIn(duration: Long = 400): ViewPropertyAnimator {

    if (alpha > 0f) {
        alpha = 0f
    }

    if (!isVisible()) {
        visible()
    }

    return animate(true).alpha(1.0f).setDuration(duration)
}

/**
 * Fades out the View
 */
fun View.fadeOut(duration: Long = 400): ViewPropertyAnimator {
    return animate(true).alpha(0.0f).setDuration(duration).withEndAction {
        gone()
    }
}

/**
 * Fades to a specific alpha between 0 to 1
 */
fun View.fadeTo(alpha: Float, duration: Long = 400): ViewPropertyAnimator {
    return animate(true).alpha(alpha).setDuration(duration)
}

/**
 * Animation: Enter from left
 */
fun View.enterFromLeft(duration: Long = 400): ViewPropertyAnimator {
    val x = this.x    // store initial x
    this.x = 0f - this.width    // move to left outside screen

    return animate(true).x(x).setDuration(duration)
}

/**
 * Animation: Enter from right
 */
fun View.enterFromRight(duration: Long = 400): ViewPropertyAnimator {
    val widthPixels = Resources.getSystem().displayMetrics.widthPixels    // get device width
    val x = this.x    // store initial x
    this.x = widthPixels.toFloat()    // move to right outside screen

    return animate(true).x(x).setDuration(duration)
}

/**
 * Animation: Enter from top
 */
fun View.enterFromTop(duration: Long = 400): ViewPropertyAnimator {
    val y = this.y    // store initial y
    this.y = 0f - this.height    // move to top

    return animate(true).y(y).setDuration(duration)
}

/**
 * Animation: Enter from bottom
 */
fun View.enterFromBottom(duration: Long = 400, callback: ((viewPropertyAnimator: ViewPropertyAnimator) -> Unit)? = null) {
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()    // get device height

    this.invisible()

    val y = this.y          // store initial y
    this.y = screenHeight   // move to bottom
    this.visible()
    callback?.invoke(animate().y(y).setDuration(duration))
}

/**
 * Animation: Exit to left
 */
fun View.exitToLeft(duration: Long = 400): ViewPropertyAnimator {
    return animate(true).x(0f - this.width).setDuration(duration)
}

/**
 * Animation: Exit to right
 */
fun View.exitToRight(duration: Long = 400): ViewPropertyAnimator {
    val widthPixels = Resources.getSystem().displayMetrics.widthPixels    // get device width

    return animate(true).x(widthPixels.toFloat()).setDuration(duration)
}

/**
 * Animation: Exit to top
 */
fun View.exitToTop(duration: Long = 400): ViewPropertyAnimator {
    return animate(true).y(0f - this.height).setDuration(duration)
}

/**
 * Animation: Exit to bottom
 */
fun View.exitToBottom(duration: Long = 400): ViewPropertyAnimator {
    val heightPixels = Resources.getSystem().displayMetrics.heightPixels    // get device height
    val y = this.y  // store initial y
    return animate(true).y(heightPixels.toFloat()).setDuration(duration).withEndAction {
        this.y = y
        this.gone()
    }
}

/**
 * Animation: Slide up its own height to its original position
 */
fun View.slideUp(duration: Long = 400): ViewPropertyAnimator {
    this.invisible()
    this.translationY = this.height.toFloat()
    this.visible()

    return animate(true).translationY(0f).setDuration(duration)
}

fun View.getLocationOnScreen(): Point {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return Point(location[0], location[1])
}

// ViewPropertyAnimator is singleton, so it is needed to reset settings in order to chain animations as wanted
fun View.animate(reset: Boolean = false): ViewPropertyAnimator {
    return this.animate().reset()
}

fun ViewPropertyAnimator.reset(): ViewPropertyAnimator {
    return this
            .setListener(null)
            .setDuration(400)
            .setStartDelay(0)
            .setInterpolator(LinearInterpolator())
}