package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by christiansteffensen on 23/05/2017.
 */

fun Context.inflate(res: Int, parent: ViewGroup? = null) : View {
    return LayoutInflater.from(this).inflate(res, parent, false)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? = if (p1 != null && p2 != null) block(p1, p2) else null
fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3) -> R?): R? = if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> safeLet(p1: T1?, p2: T2?, p3: T3?, p4: T4?, block: (T1, T2, T3, T4) -> R?): R? = if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> safeLet(p1: T1?, p2: T2?, p3: T3?, p4: T4?, p5: T5?, block: (T1, T2, T3, T4, T5) -> R?): R? = if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) block(p1, p2, p3, p4, p5) else null