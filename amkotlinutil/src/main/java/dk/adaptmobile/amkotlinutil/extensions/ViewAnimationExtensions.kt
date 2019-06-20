package dk.adaptmobile.amkotlinutil.extensions

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.res.Resources
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

/**
 * Fades in the View
 */
fun View?.fadeIn(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        if (alpha > 0f) {
            alpha = 0f
        }

        if (!isVisible()) {
            visible()
        }

        return animate(true)
                .alpha(1.0f)
                .setDuration(duration)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)

    }

    return null
}

/**
 * Fades out the View
 */
fun View?.fadeOut(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator(), invisible: Boolean = false): ViewPropertyAnimator? {
    this?.let {
        return animate(true)
                .alpha(0.0f)
                .setDuration(duration)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .withEndAction {
                    if (!invisible) this.gone() else this.invisible()
                }
    }

    return null
}

/**
 * Fades to a specific alpha between 0 to 1
 */
fun View?.fadeTo(alpha: Float, duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        return animate(true)
                .alpha(alpha)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration)
    }

    return null
}

/**
 * Animation: Enter from left
 */
fun View?.enterFromLeft(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        val x = this.x    // store initial x
        this.x = 0f - this.width    // move to left outside screen
        this.visible()

        return animate(true).x(x)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration)
    }

    return null
}

/**
 * Animation: Enter from right
 */
fun View?.enterFromRight(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels    // get device width
        val x = this.x    // store initial x
        this.x = widthPixels.toFloat()    // move to right outside screen
        this.visible()

        return animate(true).x(x)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration)
    }

    return null
}

/**
 * Animation: Enter from top
 */
fun View?.enterFromTop(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        val y = this.y    // store initial y
        this.y = 0f - this.height    // move to top
        this.visible()

        return animate(true).y(y)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration)
    }
    return null
}

/**
 * Animation: Enter from bottom
 */
fun View?.enterFromBottom(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()    // get device height
        val y = this.y          // store initial y
        this.y = screenHeight   // move to bottom
        this.visible()

        return animate().y(y)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration)
    }

    return null
}

/**
 * Animation: Exit to left
 */
fun View?.exitToLeft(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        return animate(true).x(0f - this.width)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration)
    }

    return null
}

/**
 * Animation: Exit to right
 */
fun View?.exitToRight(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels    // get device width
        return animate(true).x(widthPixels.toFloat())
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration)
    }

    return null
}

/**
 * Animation: Exit to top
 */
fun View?.exitToTop(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        val y = this.y    // store initial y
        return animate(true).y(0f - this.height)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration).withEndAction {
                    // reset to original pos
                    this.y = y
                    this.invisible()
                }
    }

    return null
}

/**
 * Animation: Exit to bottom
 */
fun View?.exitToBottom(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        val heightPixels = Resources.getSystem().displayMetrics.heightPixels    // get device height
        val y = this.y  // store initial y

        return animate(true).y(heightPixels.toFloat())
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration).withEndAction {
                    this.y = y
                    this.gone()
                }
    }

    return null
}

/**
 * Animation: Slide up its own height to its original position
 */
fun View?.slideUp(duration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()): ViewPropertyAnimator? {
    this?.let {
        this.invisible()
        this.translationY = this.height.toFloat()
        this.visible()

        return animate(true)
                .translationY(0f)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(duration)
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


fun View.rotate(rotation: Float, animated: Boolean = true, animationDuration: Long = 400, startDelay: Long = 0, interpolator: Interpolator = AccelerateDecelerateInterpolator()) {
    when (animated) {
        true -> this.animate().rotationBy(rotation)
                .setStartDelay(startDelay)
                .setInterpolator(interpolator)
                .setDuration(animationDuration)
                .start()
        false -> this.rotation = this.rotation + rotation
    }
}

fun ViewPropertyAnimator.scale(scale: Float): ViewPropertyAnimator {
    this.scaleX(scale)
    this.scaleY(scale)
    return this
}

fun View.setbackgroundColorResourceAnimated(resId: Int, duration: Long = 400) {
    val colorFrom = (this.background as ColorDrawable).color
    val colorTo = this.context.getColorCompat(resId)
    val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
    colorAnimation.duration = duration
    colorAnimation.addUpdateListener { animator -> this.setBackgroundColor(animator.animatedValue as Int) }
    colorAnimation.start()
}

fun View.fadeInUp(duration: Long = 250, offset: Float? = null) {
    this.invisible()
    afterLatestMeasured {
        this.translationY = offset ?: this.height.toFloat() - (this.height / 2)
        this.alpha = 0f
        this.visible()
        this.animate(true).translationY(0f).alpha(1f).setDuration(duration).setInterpolator(AccelerateDecelerateInterpolator())
    }
}

/**
 * Animation: Pulsating effect on a View. You can edit the speed, scale and start delay on the animation
 */
fun View.pulsate(speed: Long = 1_500L, scale: Float = 1.15f, startingDelay: Long = 1_500L) {
    ObjectAnimator.ofPropertyValuesHolder(
            this,
            PropertyValuesHolder.ofFloat("scaleX", scale),
            PropertyValuesHolder.ofFloat("scaleY", scale)
    ).apply {
        duration = speed
        repeatCount = ObjectAnimator.INFINITE
        repeatMode = ObjectAnimator.REVERSE
        startDelay = startingDelay
    }.start()
}