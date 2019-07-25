package dk.adaptmobile.amkotlinutil.model

import android.os.Handler

data class PostDelay(val handler: Handler, val runnable: Runnable) {
    fun cancel() {
        handler.removeCallbacks(runnable)
    }

}