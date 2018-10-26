package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.R.attr.right
import android.R.attr.left
import android.graphics.Bitmap
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewCompat
import android.transition.Scene
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import dk.adaptmobile.amkotlinutil.util.KotlinAnimationListener


/**
 * Created by christiansteffensen on 05/06/2017.
 */

fun View.hideKeyboard() {
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)

    requestFocus()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visibleIf(predicate: Boolean, invisible: Boolean = false) {
    when (predicate) {
        true -> this.visible()
        false -> if (!invisible) this.gone() else this.invisible()
    }
}

// TODO fix this (so it takes animation type instead)
fun View.setVisibility(visible: Boolean, animated: Boolean) {
    when (visible) {
        true -> if (animated) this.slideUp() else this.visible()
        false -> this.invisible()
    }
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.isInvisible(): Boolean {
    return this.visibility == View.INVISIBLE
}

fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

fun View.toggleVisibility() {
    if (isVisible()) gone() else visible()
}

fun View.setHeight(height: Int) {
    val params = layoutParams
    params.height = height
    layoutParams = params
}

fun View.setWidth(width: Int) {
    val params = layoutParams
    params.width = width
    layoutParams = params
}

fun View.setSize(height: Int, width: Int) {
    val params = layoutParams
    params.width = width
    params.height = height
    layoutParams = params
}

// https://antonioleiva.com/kotlin-ongloballayoutlistener/
inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    if (this != null && this.isLaidOutCompat()) {
        f()
    } else {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        })
    }
}

fun View.isLaidOutCompat(): Boolean {
    return ViewCompat.isLaidOut(this)
}

fun View.setbackgroundColorResource(@ColorRes resId: Int) {
    setBackgroundColor(context.getColorCompat(resId))
}

fun View.toBitmap(): Bitmap? { //Take "screenshot" of a view from: http://stackoverflow.com/questions/2801116/converting-a-view-to-bitmap-without-displaying-it-in-android
    this.clearFocus()
    this.isPressed = false

    val willNotCache = this.willNotCacheDrawing()
    this.setWillNotCacheDrawing(false)

    // Reset the drawing cache background color to fully transparent
    // for the duration of this operation
    val color = this.drawingCacheBackgroundColor
    this.drawingCacheBackgroundColor = 0

    if (color != 0) {
        this.destroyDrawingCache()
    }
    this.buildDrawingCache()
    val cacheBitmap = this.drawingCache ?: return null

    val bitmap = Bitmap.createBitmap(cacheBitmap)

    // Restore the view
    this.destroyDrawingCache()
    this.setWillNotCacheDrawing(willNotCache)
    this.drawingCacheBackgroundColor = color

    return bitmap
}

fun ViewGroup.getString(@StringRes stringRes: Int): String {
    return this.context.getString(stringRes)
}


fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    val params = layoutParams

    when (params) {
        is ConstraintLayout.LayoutParams -> {
            params.setMargins(left, top, right, bottom)
            this.layoutParams = params
        }
        is LinearLayout.LayoutParams -> {
            params.setMargins(left, top, right, bottom)
            this.layoutParams = params
        }
        is FrameLayout.LayoutParams -> {
            params.setMargins(left, top, right, bottom)
            this.layoutParams = params
        }
        is RelativeLayout.LayoutParams -> {
            params.setMargins(left, top, right, bottom)
            this.layoutParams = params
        }
    }
}

fun View.modifyPadding(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val l = left ?: this.paddingLeft
    val t = top ?: this.paddingTop
    val r = right ?: this.paddingRight
    val b = bottom ?: this.paddingBottom
    this.setPadding(l, t, r, b)
}

inline fun ViewPropertyAnimator.setAnimationListener(func: KotlinAnimationListener.() -> Unit): ViewPropertyAnimator {
    val listener = KotlinAnimationListener()
    listener.func()
    setListener(listener)
    return this
}

infix fun View.and(v: View): List<View> {
    return mutableListOf(this, v)
}

infix fun List<View>.and(v: View): List<View> {
    val list = mutableListOf<View>()
    list.addAll(this)
    list.add(v)
    return list
}

fun List<View>.gone() {
    this.forEach { it.gone() }
}

fun List<View>.invisible() {
    this.forEach { it.invisible() }
}

fun List<View>.visible() {
    this.forEach { it.visible() }
}
