package dk.adaptmobile.amkotlinutil.extensions

import android.animation.AnimatorSet
import android.content.Context
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.*
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

fun View?.afterLatestMeasured(callback: (v: View) -> Unit) {
    this?.post {
        if (this.isAttachedToWindow) {
            callback(this)
        }
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

fun View.modifyMargin(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val l = left ?: this.marginLeft
    val t = top ?: this.marginTop
    val r = right ?: this.marginRight
    val b = bottom ?: this.marginBottom
    this.setMargins(l, t, r, b)
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

inline fun AnimatorSet.setAnimationListener(func: KotlinAnimationListener.() -> Unit): AnimatorSet {
    val listener = KotlinAnimationListener()
    listener.func()
    addListener(listener)
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

fun View.setBackgroundTintRes(@ColorRes colorRes: Int, tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_OVER) {
    this.background.setColorFilter(context.getColorCompat(colorRes), tintMode)
}

fun View.setBackgroundTint(color: Int, tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_OVER) {
    this.background.setColorFilter(color, tintMode)
}

fun View.scale(scale: Float) {
    this.scaleX = scale
    this.scaleY = scale
}

fun View.setWidthWrapContent() {
    val params = this.layoutParams
    params.width = ViewGroup.LayoutParams.WRAP_CONTENT
    this.layoutParams = params
}

fun View.setHeightWrapContent() {
    val params = this.layoutParams
    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
    this.layoutParams = params
}

fun View.disableClipOnParents() {
    val v = this

    if (v.parent == null) {
        return
    }

    if (v is ViewGroup) {
        v.clipChildren = false
    }

    if (v.parent is View) {
        (v.parent as View).disableClipOnParents()
    }
}

fun View.getGoneHeight(): Int {
    val widthSpec = View.MeasureSpec.makeMeasureSpec(this.width, View.MeasureSpec.EXACTLY)
    val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    this.measure(widthSpec, heightSpec)
    return this.measuredHeight
}

fun View.setSemiTransparentIf(shouldBeTransparent: Boolean, disabledAlpha: Float = 0.3f) {
    alpha = when (shouldBeTransparent) {
        true -> disabledAlpha
        false -> 1f
    }
}

fun View.getGoneHeight(callback: (futureHeight: Int) -> Unit) {
    this.afterLatestMeasured {
        val originalHeight = this.height // save the original height (is most likely wrap content)

        this.setHeight(0) // "hide" the view
        this.invisible() // make the view invisible so it gets a width

        this.afterLatestMeasured {

            val originalWidth = this.width // the view now has a width

            // measure how high the view will be
            val widthSpec = View.MeasureSpec.makeMeasureSpec(originalWidth, View.MeasureSpec.EXACTLY)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            this.measure(widthSpec, heightSpec)

            val futureHeight = this.measuredHeight

            // hide the view and set back to the original height
            this.gone()
            this.setHeight(originalHeight)
            callback(futureHeight)
        }
    }
}

data class PopupMenuItem(val description: String, val value: String)

/**
 * Extension function to show a PopupMenu on a view. Optionally, pass in a separate anchor view, if you want the popup to be centered on a different view than the clicked one
 * @param   items         List of items to be shown in the PopupMenu. Description is the text to be shown in the popup, value is the text(value) that will be returned when the item is selected
 * @param   anchor        Optional anchor view. Defaults to the clicked view.
 * @param   style         Optional Style resource to style the popupmenu
 * @param   gravity       Gravity flag to control gravity, defaults to NO_GRAVITY
 * @param   itemSelected  Callback lambda with the selected value
 */
fun View.showPopupMenu(items: List<PopupMenuItem>, anchor: View = this, gravity: Int = Gravity.NO_GRAVITY, @StyleRes style: Int = 0, itemSelected: (item: String) -> Unit) {
    val contextWrapper = ContextThemeWrapper(context, style)
    val popupMenu = PopupMenu(contextWrapper, anchor, gravity)

    items.forEach {
        popupMenu.menu.add(it.description)
    }

    popupMenu.setOnMenuItemClickListener { selectedItem ->
        val item = items.first { it.description == selectedItem.title.toString() }
        itemSelected(item.value)
        return@setOnMenuItemClickListener true
    }

    popupMenu.show()
}