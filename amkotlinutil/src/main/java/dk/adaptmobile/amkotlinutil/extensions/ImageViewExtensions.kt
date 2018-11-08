package dk.adaptmobile.amkotlinutil.extensions

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.ColorRes
import android.util.Base64
import android.view.View
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

fun ImageView.setTint(@ColorRes colorRes: Int) {
    this.setColorFilter(context.getColorCompat(colorRes), android.graphics.PorterDuff.Mode.SRC_OVER)
}

fun ImageView.loadBase64Image(base64Image: String?) {
    base64Image?.let {
        //        val data = base64Image.split(',')[1] //Get the second part
        Glide.with(context)
                .asBitmap()
                .load(Base64.decode(base64Image, Base64.DEFAULT))
                .into(this)
    }
}

