package dk.adaptmobile.amkotlinutil.model

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Property
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.bluelinelabs.conductor.changehandler.AnimatorChangeHandler

/**
 * Created by Alex on 19/09/2017.
 */
class FlipChangeHandler(val flipDirection: FlipDirection, val duration: Long): AnimatorChangeHandler() {

    enum class FlipDirection private constructor(internal val inStartRotation: Float, internal val outEndRotation: Float, internal val property: Property<View, Float>) {
        LEFT(-180f, 180f, View.ROTATION_Y),
        RIGHT(180f, -180f, View.ROTATION_Y),
        UP(-180f, 180f, View.ROTATION_X),
        DOWN(180f, -180f, View.ROTATION_X)
    }

    companion object {
        val DEFAULT_ANIMATION_DURATION: Long = 300
    }

    constructor(): this(FlipDirection.RIGHT, FlipChangeHandler.DEFAULT_ANIMATION_DURATION)
    constructor(flipDirection: FlipDirection): this(flipDirection, FlipChangeHandler.DEFAULT_ANIMATION_DURATION)
    constructor(animationDuration: Long): this(FlipDirection.RIGHT, animationDuration)


    override fun getAnimator(container: ViewGroup, from: View?, to: View?, isPush: Boolean, toAddedToContainer: Boolean): Animator {
        val animatorSet = AnimatorSet()

        to?.let {
            val rotation = ObjectAnimator.ofFloat(it, flipDirection.property, flipDirection.inStartRotation, 0f).setDuration(duration)
            rotation.interpolator = AccelerateDecelerateInterpolator()
            animatorSet.play(rotation)

            val alpha = ObjectAnimator.ofFloat(it, View.ALPHA, 1f).setDuration(duration/2)
            alpha.startDelay = duration / 3
            animatorSet.play(alpha)
        }

        from?.let {
            val rotation = ObjectAnimator.ofFloat(it, flipDirection.property, 0f, flipDirection.outEndRotation).setDuration(duration)
            rotation.interpolator = AccelerateDecelerateInterpolator()
            animatorSet.play(rotation)

            val alpha = ObjectAnimator.ofFloat(it, View.ALPHA, 0f).setDuration(duration/2)
            alpha.startDelay = duration / 3
            animatorSet.play(alpha)
        }

        return animatorSet
    }


    override fun resetFromView(from: View) {
        from.alpha = 1f

        when(this.flipDirection) {
            FlipDirection.LEFT -> {from.rotationY = 0f;}
            FlipDirection.RIGHT -> {from.rotationY = 0f;}
            FlipDirection.UP -> {from.rotationX = 0f;}
            FlipDirection.DOWN -> { from.rotationX = 0f;}
        }
    }
}
