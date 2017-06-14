package dk.adaptmobile.amkotlinutil.extensions

import android.app.Dialog
import android.view.View
import android.widget.TextView

/**
 * Created by Thomas on 07/06/2017.
 */
fun Dialog.setCustomTitle(resId: Int, title: String) {
    val titleView = findViewById(resId) as TextView
    titleView.text = title
}

fun Dialog.setCustomText(resId: Int, message: String) {
    val messageView = findViewById(resId) as TextView
    messageView.text = message
}

fun Dialog.setConfirmButtonText(resId: Int, text: String) {
    val confirmButton = findViewById(resId) as TextView
    confirmButton.text = text
}

fun Dialog.setCancelButtonText(resId: Int, text: String) {
    val cancelButton = findViewById(resId) as TextView
    cancelButton.text = text
}

fun Dialog.setConfirmButtonOnClick(resId: Int, clickListener: View.OnClickListener) {
    val confirmButton = findViewById(resId) as TextView
    confirmButton.setOnClickListener(clickListener)
}

fun Dialog.setCancelButtonOnClick(resId: Int, clickListener: View.OnClickListener) {
    val cancelButton = findViewById(resId) as TextView
    cancelButton.setOnClickListener(clickListener)
}