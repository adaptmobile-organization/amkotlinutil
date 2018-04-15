package dk.adaptmobile.amkotlinutil.conductor

import android.annotation.TargetApi
import android.os.Build
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionSet
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.changehandler.TransitionChangeHandler
import jp.wasabeef.blurry.Blurry

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class DialogChangeHandler : TransitionChangeHandler() {

    override fun getTransition(container: ViewGroup, from: View?, to: View?, isPush: Boolean): Transition {

        if (isPush) {
            Blurry.with(from?.context).radius(25).sampling(2).async().onto(from as ViewGroup)
        } else {
            Blurry.delete(to as ViewGroup)
        }

        val transition = TransitionSet()
                .setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
                .addTransition(Fade(Fade.OUT).addTarget(from))
                .addTransition(Fade(Fade.IN).addTarget(to))


        return transition
    }

    override fun removesFromViewOnPush(): Boolean {
        return false
    }

}
