package dk.adaptmobile.amkotlinutil.extensions

import android.content.Intent
import android.net.Uri
import com.bluelinelabs.conductor.Controller

fun Controller.composeEmail(addresses: Array<String>, subject: String, emailText: String, chooserText: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        type = "text/html"
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, addresses)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, emailText)
    }

    this.startActivity(Intent.createChooser(intent, chooserText))
}