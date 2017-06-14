package dk.adaptmobile.amkotlinutil.extensions

import android.support.annotation.StringRes
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler

/**
 * Created by christiansteffensen on 05/06/2017.
 */

enum class AnimationType {
    SLIDE, FADE, BOTTOM, NONE
}

fun Router.setFadeChangeHandler(transaction: RouterTransaction) {
    transaction.pushChangeHandler(FadeChangeHandler()).popChangeHandler(FadeChangeHandler())
}

fun Router.pushView(controller: Controller, retain: Boolean, asRoot: Boolean, type: AnimationType) {
    if (retain) {
        controller.retainViewMode = Controller.RetainViewMode.RETAIN_DETACH
    }

    controller.hideKeyboard()

    val transaction = RouterTransaction.with(controller)
    when (type) {
        AnimationType.SLIDE -> {
            transaction.pushChangeHandler(HorizontalChangeHandler(true))
            transaction.popChangeHandler(HorizontalChangeHandler(true))
        }
        AnimationType.BOTTOM -> {
            transaction.pushChangeHandler(VerticalChangeHandler(true))
            transaction.popChangeHandler(VerticalChangeHandler(true))
        }
        AnimationType.FADE -> {
            transaction.pushChangeHandler(FadeChangeHandler(true))
            transaction.popChangeHandler(FadeChangeHandler(true))
        }
        AnimationType.NONE -> {
            transaction.pushChangeHandler()
            transaction.popChangeHandler()
        }
        else -> {
            transaction.pushChangeHandler()
            transaction.popChangeHandler()
        }
    }

    if(asRoot) {
        this.setRoot(transaction)
    } else {
        this.pushController(transaction)
    }

}


fun Controller.getString(@StringRes stringRes: Int): String? {
    return resources?.getString(stringRes)
}

fun Controller.hideKeyboard() {
    view?.hideKeyboard()
}