package dk.adaptmobile.amkotlinutil.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.CropCircleTransformation

/**
 * Created by christiansteffensen on 05/06/2017.
 */

fun ImageView.loadImageResource(imageResource: String?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).into(this)
    }
}

fun ImageView.loadImageResource(imageResource: Int?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).into(this)
    }
}

fun ImageView.loadImageResource(imageResource: Bitmap?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).into(this)
    }
}

fun ImageView.loadImageResource(imageResource: Drawable?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).into(this)
    }
}

fun ImageView.loadImageResourceCenterCrop(imageResource: String?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).apply(RequestOptions().centerCrop()).into(this)
    }
}

fun ImageView.loadImageResourceCenterCrop(imageResource: Int?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).apply(RequestOptions().centerCrop()).into(this)
    }
}

fun ImageView.loadImageResourceAsCircle(imageResource: String?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).apply(RequestOptions().circleCrop()).into(this)
    }
}

fun ImageView.loadImageResourceAsCircle(imageResource: Int?) {
    imageResource?.let {
        Glide.with(context).load(imageResource).apply(RequestOptions().circleCrop()).into(this)
    }
}