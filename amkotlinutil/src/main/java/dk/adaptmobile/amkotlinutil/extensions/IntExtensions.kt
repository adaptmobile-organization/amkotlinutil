package dk.adaptmobile.amkotlinutil.extensions

import android.content.Context
import android.util.TypedValue

/**
 * Created by bjarkeseverinsen on 20/09/2017.
 */

fun Int.getString(context: Context?): String? {
    return context?.getString(this)
}
