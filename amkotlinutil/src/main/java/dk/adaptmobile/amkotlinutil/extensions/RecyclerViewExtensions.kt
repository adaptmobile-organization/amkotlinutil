package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import android.support.v7.widget.RecyclerView

val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context
