package dk.adaptmobile.amkotlinutil.extensions

import android.graphics.Typeface
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import androidx.core.content.res.ResourcesCompat
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.widget.TextView
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan
import uk.co.chrisjenx.calligraphy.CalligraphyUtils

/**
 * Created by christiansteffensen on 05/06/2017.
 */

fun TextView.applyFont(@StringRes stringRes: Int) {
    CalligraphyUtils.applyFontToTextView(context, this, context.getString(stringRes))
}

fun TextInputLayout.clearError() {
    error = null
    isErrorEnabled = false
}

val TextView.textString: String
    get() = text.toString()

fun TextView.setTextWithDifferentFonts(firstText : String, secondText : String, typeFaceFirst : CalligraphyTypefaceSpan, typeFaceSecond : CalligraphyTypefaceSpan, sizeFirst : Int, siceSecond : Int) {

    val sBuilder = SpannableStringBuilder()

    sBuilder.append(firstText)
            .append(" ") //Insert a space
            .append(secondText)

    sBuilder.setSpan(typeFaceFirst, 0, firstText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    sBuilder.setSpan(AbsoluteSizeSpan(sizeFirst), 0, firstText.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

    sBuilder.setSpan(typeFaceSecond, firstText.length + 1, firstText.length + secondText.length + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    sBuilder.setSpan(AbsoluteSizeSpan(siceSecond), firstText.length + 1, firstText.length + secondText.length + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

    this.text = sBuilder
}

fun TextView.setTextColorId(id: Int){
    this.setTextColor(this.context.getColorCompat(id))
}

fun TextView.setRightDrawable(@DrawableRes resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(0,0,resId,0)
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