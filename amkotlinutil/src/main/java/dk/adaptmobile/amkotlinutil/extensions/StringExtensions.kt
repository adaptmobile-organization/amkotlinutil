package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.telephony.PhoneNumberUtils
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun String.capitalizeFirstLetter(): String {
    return this.substring(0, 1).toUpperCase() + this.substring(1)
}

fun String.toDate(pattern: String, locale: Locale = danishLocale()): Date {
    return SimpleDateFormat(pattern, locale).parse(this)
}

fun String.parseDate(pattern: String): Date {
    return this.toDate(pattern)
}

fun String?.openInBrowser(context: Context?) {
    if (this != null && this.isNotEmpty()) {
        val page = Uri.parse(this)
        val intent = Intent(Intent.ACTION_VIEW, page)

        if (intent.resolveActivity(context?.packageManager) != null) {
            context?.startActivity(intent)
        }
    }
}

fun String.isPhoneNumber(): Boolean {
    return PhoneNumberUtils.isGlobalPhoneNumber(this)
}

fun String.removeSpaces(): String {
    return this.replace(" ", "")
}

fun String.isDanishPhoneNumber(): Boolean {
    var number = this.removeSpaces()
    val isPhoneNumber = number.isPhoneNumber()

    when {
        number.startsWith("+45") -> number = number.substring(3)
        number.startsWith("0045") -> number = number.substring(4)
        number.startsWith("45") -> number = number.substring(2)
    }

    return isPhoneNumber && number.length == 8
}

fun String.cleanedDanishPhoneNumber(): String {
    var number = this.removeSpaces()

    when {
        number.startsWith("+45") -> number = number.substring(3)
        number.startsWith("0045") -> number = number.substring(4)
        number.startsWith("45") -> number = number.substring(2)
    }

    return number
}

fun String.isEmail(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun String?.orText(text: String) = this ?: text

fun String.versionNumberToInt(): Int {
    return split(".").joinToString("").toInt()
}

fun String.capitalizeFirstLetterEachWord(): String {
    return this.toLowerCase()
            .split(" ")
            .joinToString(" ") { it.capitalize() }
}

fun String?.convertToLongId(): Long { // should return different value for each not-same string
    if (this == null) {
        return 0
    }
    var `val`: Long = 37
    var i = 0
    val size = this.length
    while (i < size) {
        val c = this[i]
        `val` += (37 * c.toInt() + (i + 1) * 41).toLong()
        i++
    }
    return `val`
}

fun String.toColor(): Int? {
    if (this.isNotEmpty()) {
        return Color.parseColor(this)
    } else {
        return null
    }
}

fun String.urlEncoded(): String? = URLEncoder.encode(this, "utf-8")

