package dk.adaptmobile.amkotlinutil.extensions

import android.graphics.Typeface
import android.text.*
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by christiansteffensen on 05/06/2017.
 */


fun TextInputLayout.clearError() {
    error = null
    isErrorEnabled = false
}

val TextView.textString: String
    get() = text.toString()


fun TextView.setTextColorId(id: Int) {
    this.setTextColor(this.context.getColorCompat(id))
}

fun TextView.setRightDrawable(@DrawableRes resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0)
}

fun TextView.setFont(@FontRes font: Int) {
    this.typeface = context.getFontCompat(font)
}

fun TextView.setFont(typeface: Typeface?) {
    this.typeface = typeface
}

fun TextView.ellipsizeDynamic(text: String) {
    this.text = text
    this.afterLatestMeasured {
        val noOfLinesVisible = this.height / (this.lineHeight)
        this.maxLines = noOfLinesVisible
        this.ellipsize = TextUtils.TruncateAt.END
    }
}

fun TextView.ellipsizeViewPager(text: String) {
    this.text = text
    this.afterLatestMeasured {
        val noOfLinesVisible = this.height / (this.lineHeight.toDouble() * 0.84).toInt()
        this.maxLines = noOfLinesVisible
        this.ellipsize = TextUtils.TruncateAt.END
    }
}

fun TextView.setTextColorFromStartTilIndex(index: Int, text: String, @ColorRes color: Int): TextView {
    val spannable = SpannableString(text)
    val color = ContextCompat.getColor(context, color)
    if (index <= text.length) {
        spannable.setSpan(ForegroundColorSpan(color), 0, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    } else {
        spannable.setSpan(ForegroundColorSpan(color), 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    this.setText(spannable, TextView.BufferType.SPANNABLE)
    return this
}

fun TextView.setTextColorForWordInText(text: String, coloredText: String, @ColorRes color: Int): TextView {
    val spannable = SpannableString(text)
    val color = ContextCompat.getColor(context, color)
    val startIndex = text.indexOf(coloredText)
    val lastIndex = startIndex + coloredText.length
    spannable.setSpan(ForegroundColorSpan(color), startIndex, lastIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.setText(spannable, TextView.BufferType.SPANNABLE)
    return this
}
