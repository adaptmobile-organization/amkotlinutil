package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
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

/**
 * Adds pagination to a RecyclerView
 * @param isLoading Pass in a variable that defines if loading is already happening
 * @param noMoreResults Pass in a variable that defines if there are no more results
 * @param nextPage Will be called each time a new page should be loaded
 */
fun RecyclerView.addPagination(isLoading: () -> Boolean, noMoreResults: () -> Boolean, nextPage: () -> Unit,  offset: Int) {
    val offset = offset
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lm = layoutManager as LinearLayoutManager
            val visibleItemCount = lm.childCount
            val totalItemCount = lm.itemCount
            val firstVisibleItemPosition = lm.findFirstVisibleItemPosition()

            if (!isLoading() && !noMoreResults()) {
                if (visibleItemCount + firstVisibleItemPosition >= (totalItemCount - offset) && firstVisibleItemPosition >= 0) {
                    nextPage()
                }
            }
        }
    })
}
