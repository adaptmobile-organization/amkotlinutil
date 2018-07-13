package dk.adaptmobile.amkotlinutil.extensions

import android.util.Base64

fun ByteArray?.toBase64(): String? {
    return Base64.encodeToString(this, Base64.NO_WRAP)
}
