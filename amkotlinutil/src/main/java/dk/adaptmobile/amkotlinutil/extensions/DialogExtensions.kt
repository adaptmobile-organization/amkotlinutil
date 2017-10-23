package dk.adaptmobile.amkotlinutil.extensions

import android.app.Dialog
import android.view.View
import android.widget.TextView

/**
 * Created by Thomas on 07/06/2017.
 */
fun Dialog.setCustomTitle(resId: Int, title: String) {
    val titleView: TextView = findViewById(resId)
    titleView.text = title
}

fun Dialog.setCustomText(resId: Int, message: String) {
    val messageView: TextView = findViewById(resId)
    messageView.text = message
}

fun Dialog.setConfirmButtonText(resId: Int, text: String) {
    val confirmButton: TextView = findViewById(resId)
    confirmButton.text = text
}

fun Dialog.setCancelButtonText(resId: Int, text: String) {
    val cancelButton: TextView = findViewById(resId)
    cancelButton.text = text
}

fun Dialog.setConfirmButtonOnClick(resId: Int, clickListener: View.OnClickListener) {
    val confirmButton: TextView = findViewById(resId)
    confirmButton.setOnClickListener(clickListener)
}

fun Dialog.setCancelButtonOnClick(resId: Int, clickListener: View.OnClickListener) {
    val cancelButton: TextView = findViewById(resId)
    cancelButton.setOnClickListener(clickListener)
}
