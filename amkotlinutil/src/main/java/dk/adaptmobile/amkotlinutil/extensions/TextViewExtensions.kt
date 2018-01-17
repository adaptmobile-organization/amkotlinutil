package dk.adaptmobile.amkotlinutil.extensions

import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.widget.EditText
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