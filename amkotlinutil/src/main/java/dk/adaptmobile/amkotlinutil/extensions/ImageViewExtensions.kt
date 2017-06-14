package dk.adaptmobile.amkotlinutil.extensions

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.CropCircleTransformation

/**
 * Created by christiansteffensen on 05/06/2017.
 */

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadUrlAsCircle(url : String?) {
    Glide.with(context).load(url).bitmapTransform(CropCircleTransformation(context)).into(this)
}

fun ImageView.setImageUriAsCircle(uri: Uri) {
    Glide.with(context).load(uri).bitmapTransform(CropCircleTransformation(context)).into(this)
}