package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context

fun RecyclerView.disableAnimations() {
    this.itemAnimator?.addDuration = 0
    this.itemAnimator?.removeDuration = 0
    this.itemAnimator?.moveDuration = 0
    this.itemAnimator?.changeDuration = 0
}

fun RecyclerView.enableAnimations() {
    this.itemAnimator?.addDuration = 120
    this.itemAnimator?.removeDuration = 120
    this.itemAnimator?.moveDuration = 250
    this.itemAnimator?.changeDuration = 250
}
