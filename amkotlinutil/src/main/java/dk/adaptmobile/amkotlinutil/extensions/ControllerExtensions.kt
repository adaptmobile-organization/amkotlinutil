package dk.adaptmobile.amkotlinutil.extensions

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.bluelinelabs.conductor.Controller
import com.github.ajalt.timberkt.e

fun Controller.composeEmail(addresses: Array<String>, subject: String, emailText: String, chooserText: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        type = "text/html"
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, addresses)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, emailText)
    }
    try {
        this.startActivity(Intent.createChooser(intent, chooserText))
    } catch (ex: ActivityNotFoundException) {
        e { "Unable to find market app: $ex" }
    }
}