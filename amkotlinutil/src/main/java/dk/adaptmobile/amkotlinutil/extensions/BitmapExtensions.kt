package dk.adaptmobile.amkotlinutil.extensions

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap?.toByteArray(): ByteArray? {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}

fun Bitmap?.toBase64(): String? {
    return this.toByteArray().toBase64()
}