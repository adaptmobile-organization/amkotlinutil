package dk.adaptmobile.amkotlinutil.extensions

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import jp.wasabeef.recyclerview.animators.BaseItemAnimator

fun GroupAdapter<GroupieViewHolder>.addWithAnimation(recyclerView: RecyclerView, group: Group, animator: BaseItemAnimator, viewAttached: () -> Unit) {
    val currentAnimator = recyclerView.itemAnimator
    recyclerView.itemAnimator = animator
    recyclerView.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
        }

        override fun onChildViewAttachedToWindow(view: View) {
            viewAttached()
            recyclerView.removeOnChildAttachStateChangeListener(this)
        }
    })
    this.add(group)
    wait(animator.addDuration) { // The animator can't be changed back until it's done animating
        recyclerView.itemAnimator = currentAnimator
    }
}
