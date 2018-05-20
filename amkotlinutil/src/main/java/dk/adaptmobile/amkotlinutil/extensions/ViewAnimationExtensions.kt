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
fun View?.fadeIn(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        if (alpha > 0f) {
            alpha = 0f
        }

        if (!isVisible()) {
            visible()
        }

        return animate(true).alpha(1.0f).setDuration(duration)
    }

    return null
}

/**
 * Fades out the View
 */
fun View?.fadeOut(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        return animate(true).alpha(0.0f).setDuration(duration).withEndAction {
            gone()
        }
    }

    return null
}

/**
 * Fades to a specific alpha between 0 to 1
 */
fun View?.fadeTo(alpha: Float, duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        return animate(true).alpha(alpha).setDuration(duration)
    }

    return null
}

/**
 * Animation: Enter from left
 */
fun View?.enterFromLeft(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        val x = this.x    // store initial x
        this.x = 0f - this.width    // move to left outside screen
        this.visible()

        return animate(true).x(x).setDuration(duration)
    }

    return null
}

/**
 * Animation: Enter from right
 */
fun View?.enterFromRight(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels    // get device width
        val x = this.x    // store initial x
        this.x = widthPixels.toFloat()    // move to right outside screen
        this.visible()

        return animate(true).x(x).setDuration(duration)
    }

    return null
}

/**
 * Animation: Enter from top
 */
fun View?.enterFromTop(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        val y = this.y    // store initial y
        this.y = 0f - this.height    // move to top
        this.visible()

        return animate(true).y(y).setDuration(duration)
    }
    return null
}

/**
 * Animation: Enter from bottom
 */
fun View?.enterFromBottom(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()    // get device height
        val y = this.y          // store initial y
        this.y = screenHeight   // move to bottom
        this.visible()

        return animate().y(y).setDuration(duration)
    }

    return null
}

/**
 * Animation: Exit to left
 */
fun View?.exitToLeft(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        return animate(true).x(0f - this.width).setDuration(duration)
    }

    return null
}

/**
 * Animation: Exit to right
 */
fun View?.exitToRight(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels    // get device width
        return animate(true).x(widthPixels.toFloat()).setDuration(duration)
    }

    return null
}

/**
 * Animation: Exit to top
 */
fun View?.exitToTop(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        return animate(true).y(0f - this.height).setDuration(duration)
    }

    return null
}

/**
 * Animation: Exit to bottom
 */
fun View?.exitToBottom(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        val heightPixels = Resources.getSystem().displayMetrics.heightPixels    // get device height
        val y = this.y  // store initial y

        return animate(true).y(heightPixels.toFloat()).setDuration(duration).withEndAction {
            this.y = y
            this.gone()
        }
    }

    return null
}

/**
 * Animation: Slide up its own height to its original position
 */
fun View?.slideUp(duration: Long = 400): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        this.translationY = this.height.toFloat()
        this.visible()

        return animate(true).translationY(0f).setDuration(duration)
    }
    return null
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