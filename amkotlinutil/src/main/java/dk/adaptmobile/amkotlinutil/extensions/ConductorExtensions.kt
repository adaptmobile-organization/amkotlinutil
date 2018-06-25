package dk.adaptmobile.amkotlinutil.extensions

import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import dk.adaptmobile.amkotlinutil.conductor.ArcFadeMoveChangeHandler
import dk.adaptmobile.amkotlinutil.conductor.DialogChangeHandler
import dk.adaptmobile.amkotlinutil.conductor.FlipChangeHandler
import dk.adaptmobile.amkotlinutil.conductor.ScaleFadeChangeHandler

/**
 * Created by christiansteffensen on 05/06/2017.
 */

fun Router.setFadeChangeHandler(transaction: RouterTransaction) {
    transaction.pushChangeHandler(FadeChangeHandler()).popChangeHandler(FadeChangeHandler())
}

sealed class AnimationType {
    object Slide : AnimationType()
    object Fade : AnimationType()
    object Bottom : AnimationType()
    object FlipRight : AnimationType()
    object FlipLeft : AnimationType()
    object FlipUp : AnimationType()
    object FlipDown : AnimationType()
    object SharedTransition : AnimationType()
    object ScaleFade : AnimationType()
    object Dialog : AnimationType()
    object None : AnimationType()
    data class Custom(val pushControllerChangeHandler: ControllerChangeHandler, val popControllerChangeHandler: ControllerChangeHandler? = null) : AnimationType()
}

fun Router.pushView(controller: Controller?, type: AnimationType, removesFromViewOnPush: Boolean = true, retain: Boolean = false, asRoot: Boolean = false, replace: Boolean = false, tag: String? = null, hidekeyboard: Boolean = true) {
    controller?.let {

        val transaction = RouterTransaction.with(controller)

        when {
            retain -> controller.retainViewMode = Controller.RetainViewMode.RETAIN_DETACH
            hidekeyboard -> controller.hideKeyboard()
            !tag.isNullOrEmpty() -> transaction.tag(tag)
        }

        when (type) {
            is AnimationType.Slide -> {
                transaction.pushChangeHandler(HorizontalChangeHandler(removesFromViewOnPush))
                transaction.popChangeHandler(HorizontalChangeHandler(removesFromViewOnPush))
            }
            is AnimationType.Bottom -> {
                transaction.pushChangeHandler(VerticalChangeHandler(removesFromViewOnPush))
                transaction.popChangeHandler(VerticalChangeHandler(removesFromViewOnPush))
            }
            is AnimationType.Fade -> {
                transaction.pushChangeHandler(FadeChangeHandler(removesFromViewOnPush))
                transaction.popChangeHandler(FadeChangeHandler(removesFromViewOnPush))
            }
            is AnimationType.FlipRight -> {
                transaction.pushChangeHandler(FlipChangeHandler())
                transaction.popChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.LEFT))
            }
            is AnimationType.FlipLeft -> {
                transaction.pushChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.LEFT))
                transaction.popChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.RIGHT))
            }
            is AnimationType.FlipUp -> {
                transaction.pushChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.UP))
                transaction.popChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.DOWN))
            }
            is AnimationType.FlipDown -> {
                transaction.pushChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.DOWN))
                transaction.popChangeHandler(FlipChangeHandler(FlipChangeHandler.FlipDirection.UP))
            }
            is AnimationType.SharedTransition -> {
                transaction.pushChangeHandler(ArcFadeMoveChangeHandler())
                transaction.popChangeHandler(ArcFadeMoveChangeHandler())
            }
            is AnimationType.ScaleFade -> {
                transaction.pushChangeHandler(ScaleFadeChangeHandler())
                transaction.popChangeHandler(ScaleFadeChangeHandler())
            }
            is AnimationType.Dialog -> {
                transaction.pushChangeHandler(DialogChangeHandler())
                transaction.popChangeHandler(DialogChangeHandler())
            }
            is AnimationType.None -> {
                transaction.pushChangeHandler(SimpleSwapChangeHandler())
                transaction.popChangeHandler(SimpleSwapChangeHandler())
            }
            is AnimationType.Custom -> {
                transaction.pushChangeHandler(type.pushControllerChangeHandler)
                if (type.popControllerChangeHandler == null) {
                    transaction.popChangeHandler(type.pushControllerChangeHandler.copy())
                } else {
                    transaction.popChangeHandler(type.popControllerChangeHandler)
                }
            }
        }

        when {
            asRoot -> this.setRoot(transaction)
            replace -> this.replaceTopController(transaction)
            else -> this.pushController(transaction)
        }
    }
}

val Router.lastController: Controller?
    get() = if (!this.backstack.isEmpty()) this.backstack.last().controller() else null

fun Controller.getString(@StringRes stringRes: Int): String? {
    return resources?.getString(stringRes)
}

fun Controller.hideKeyboard() {
    view?.hideKeyboard()
}

fun Controller.getDimenstion(@DimenRes dimenRes: Int): Float? {
    return resources?.getDimension(dimenRes)
}

var Controller.brightness: Float?
    get() = this.activity?.window?.attributes?.screenBrightness
    set(value) {
        val window = this.activity?.window
        val layoutParams = window?.attributes
        layoutParams?.screenBrightness = value //0 is turned off, 1 is full brightness
        window?.attributes = layoutParams
    }