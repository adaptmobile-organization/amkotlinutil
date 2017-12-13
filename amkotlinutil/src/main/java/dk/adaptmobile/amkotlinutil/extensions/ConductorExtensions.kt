package dk.adaptmobile.amkotlinutil.extensions

import android.support.annotation.StringRes
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import dk.adaptmobile.amkotlinutil.model.FlipChangeHandler

/**
 * Created by christiansteffensen on 05/06/2017.
 */

enum class AnimationType {
    SLIDE, FADE, BOTTOM, FLIP_RIGHT, FLIP_LEFT, FLIP_UP, FLIP_DOWN, NONE
}

fun Router.setFadeChangeHandler(transaction: RouterTransaction) {
    transaction.pushChangeHandler(FadeChangeHandler()).popChangeHandler(FadeChangeHandler())
}

fun Router.pushView(controller: Controller, type: AnimationType, retain: Boolean = false, asRoot: Boolean = false, tag: String? = null, hidekeyboard: Boolean = true) {
    if (retain) {
        controller.retainViewMode = Controller.RetainViewMode.RETAIN_DETACH
    }

    if (hidekeyboard) {
        controller.hideKeyboard()
    }

    val transaction = RouterTransaction.with(controller)

    if (!tag.isNullOrEmpty()) {
        transaction.tag(tag)
    }

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
        AnimationType.FLIP_RIGHT -> {
            transaction.pushChangeHandler(FlipChangeHandler())
            transaction.popChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.LEFT))
        }
        AnimationType.FLIP_LEFT -> {
            transaction.pushChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.LEFT))
            transaction.popChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.RIGHT))
        }
        AnimationType.FLIP_UP -> {
            transaction.pushChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.UP))
            transaction.popChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.DOWN))
        }
        AnimationType.FLIP_DOWN -> {
            transaction.pushChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.DOWN))
            transaction.popChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.UP))
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

    if (asRoot) {
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
