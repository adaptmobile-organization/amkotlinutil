package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.R.attr.right
import android.R.attr.left
import android.graphics.Bitmap
import android.support.annotation.StringRes
import android.support.v4.view.ViewCompat
import android.view.ViewGroup
import android.widget.LinearLayout


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

fun View.setVisibility(visible: Boolean) {
    when (visible) {
        true -> this.visible()
        false -> this.gone()
    }
}

fun View.setInvisibility(visible: Boolean) {
    when (visible) {
        true -> this.visible()
        false -> this.invisible()
    }
}

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

fun View.toggle() {
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

fun View.setbackgroundColorResource(resId: Int) {
    setBackgroundColor(context.getColorCompat(id))
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

fun ViewGroup.getString(@StringRes stringRes: Int): String? {
    return this.context.getString(stringRes)
}