package dk.adaptmobile.amkotlinutil.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.util.DisplayMetrics
import android.util.Size
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.bluelinelabs.conductor.Controller
import com.github.ajalt.timberkt.e
import java.util.*

/**
 * Created by Alex on 12/09/2017.
 */

fun Activity.hideKeyboard(view: View? = currentFocus): Boolean {
    view?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
    return false
}

fun Activity?.showKeyboard(v: View) {
    this?.let {
        val inputMethodManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Activity?.toggleKeyboard() {
    this?.let {
        val inputMethodManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

var Activity.brightness: Float?
    get() = this.window?.attributes?.screenBrightness
    set(value) {
        val window = this.window
        val layoutParams = window.attributes
        layoutParams?.screenBrightness = value // 0 is turned off, 1 is full brightness
        window?.attributes = layoutParams
    }

// fixes a datepicker bug on Lollipop Galaxy S4, S5 and Note 3, maybe more. (https://stackoverflow.com/questions/28618405/datepicker-crashes-on-my-device-when-clicked-with-personal-app)
fun Activity.datePickerContext(): ContextWrapper {
    return object : ContextWrapper(this) {

        private var wrappedResources: Resources? = null

        override fun getResources(): Resources {
            val r = super.getResources()
            if (wrappedResources == null) {
                wrappedResources = object : Resources(r.assets, r.displayMetrics, r.configuration) {
                    @Throws(Resources.NotFoundException::class)
                    override fun getString(id: Int, vararg formatArgs: Any): String {
                        try {
                            return super.getString(id, *formatArgs)
                        } catch (ifce: IllegalFormatConversionException) {
                            e {"IllegalFormatConversionException Fixed! $ifce" }
                            var template = super.getString(id)
                            template = template.replace(("%" + ifce.conversion).toRegex(), "%s")
                            return String.format(configuration.locale, template, *formatArgs)
                        }
                    }
                }
            }
            return wrappedResources as Resources
        }
    }
}

fun Activity.enableFullScreen() {
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity.getScreenDimensions(): Size {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val height = displayMetrics.heightPixels.toFloat()
    val width = displayMetrics.widthPixels.toFloat()
    val convertHeight = height.toInt()
    val convertWidth = width.toInt()
    return Size(convertWidth, convertHeight)
}

fun Activity?.openNativeShareDialog(url: String, subject: String = "", chooserText: String) {
    this ?: return
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        putExtra(Intent.EXTRA_TEXT, url)
        putExtra(Intent.EXTRA_SUBJECT, subject)
    }

    this.startActivity(Intent.createChooser(intent, chooserText))
}

