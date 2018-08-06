package dk.adaptmobile.amkotlinutil.extensions

import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.ViewGroup

fun ViewGroup.startTransition(transition: TransitionSet) {
    TransitionManager.go(Scene(this), transition)
}

fun TransitionSet.setOnTransitionEndListener(callback: () -> Unit) {
    this.addListener(object : Transition.TransitionListener {
        override fun onTransitionEnd(p0: Transition?) {
            callback()
        }

        override fun onTransitionResume(p0: Transition?) {
        }

        override fun onTransitionPause(p0: Transition?) {
        }

        override fun onTransitionCancel(p0: Transition?) {
        }

        override fun onTransitionStart(p0: Transition?) {
        }
    })
}


