package dk.adaptmobile.amkotlinutil.util

import android.animation.Animator

class KotlinAnimationListener : Animator.AnimatorListener {

    private var onAnimationCancel: ((animation: Animator?) -> Unit)? = null
    private var onAnimationRepeat: ((animation: Animator?) -> Unit)? = null
    private var onAnimationEnd: ((animation: Animator?) -> Unit)? = null
    private var onAnimationStart: ((animation: Animator?) -> Unit)? = null

    override fun onAnimationRepeat(animation: Animator?) {
        onAnimationRepeat?.invoke(animation)
    }

    fun onAnimationRepeat(func: (animation: Animator?) -> Unit) {
        onAnimationRepeat = func
    }

    override fun onAnimationEnd(animation: Animator?) {
        onAnimationEnd?.invoke(animation)
    }

    fun onAnimationEnd(func: (animation: Animator?) -> Unit) {
        onAnimationEnd = func
    }

    override fun onAnimationStart(animation: Animator?) {
        onAnimationStart?.invoke(animation)
    }

    fun onAnimationStart(func: (animation: Animator?) -> Unit) {
        onAnimationStart = func
    }

    override fun onAnimationCancel(animation: Animator?) {
        onAnimationCancel?.invoke(animation)
    }

    fun onAnimationCancel(func: (animation: Animator?) -> Unit) {
        onAnimationCancel = func
    }

}