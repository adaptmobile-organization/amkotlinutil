package dk.adaptmobile.amkotlinutil.conductor

import android.view.View
import android.view.ViewGroup
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionSet
import com.bluelinelabs.conductor.changehandler.androidxtransition.TransitionChangeHandler

class DialogFadeChangeHandler(val removesFromViewOnPush: Boolean = false) : TransitionChangeHandler() {

    override fun getTransition(container: ViewGroup, from: View?, to: View?, isPush: Boolean): Transition {
        val transition = TransitionSet()
        val from = from ?: return transition
        val to = to ?: return transition

        transition
                .setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
                .addTransition(Fade(Fade.OUT).addTarget(from))
                .addTransition(Fade(Fade.IN).addTarget(to))

        return transition
    }

    override fun removesFromViewOnPush(): Boolean {
        return removesFromViewOnPush
    }
}
