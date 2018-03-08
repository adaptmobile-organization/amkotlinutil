package dk.adaptmobile.amkotlinutil.extensions

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.CropCircleTransformation

/**
 * Created by christiansteffensen on 05/06/2017.
 */

fun ImageView.loadImageResource(imageResource: String?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).into(this)
    }
}

fun ImageView.loadImageResourceAsCircle(imageResource: String?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).bitmapTransform(CropCircleTransformation(context)).into(this)
    }
}