package dk.adaptmobile.amkotlinutil.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Base64
import android.widget.ImageView
import androidx.annotation.ColorRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by christiansteffensen on 05/06/2017.
 */
sealed class Transformation {
    object CenterCrop : Transformation()
    object Circle : Transformation()
}

fun ImageView.loadImageResource(imageResource: String?, skipMemoryCache: Boolean = false, transformation: Transformation? = null) {
    imageResource?.let {
        loadImage(it, skipMemoryCache, transformation)
    }
}

fun ImageView.loadImageResource(imageResource: Int?, skipMemoryCache: Boolean = false, transformation: Transformation? = null) {
    imageResource?.let {
        loadImage(it, skipMemoryCache, transformation)
    }
}

fun ImageView.loadImageResource(imageResource: Bitmap?, skipMemoryCache: Boolean = false, transformation: Transformation? = null) {
    imageResource?.let {
        loadImage(it, skipMemoryCache, transformation)
    }
}

fun ImageView.loadImageResource(imageResource: Drawable?, skipMemoryCache: Boolean = false, transformation: Transformation? = null) {
    imageResource?.let {
        loadImage(it, skipMemoryCache, transformation)
    }
}

private fun ImageView.loadImage(imageResource: Any, skipMemoryCache: Boolean, transformation: Transformation?) {
    var requestOptions = RequestOptions()
            .skipMemoryCache(skipMemoryCache)

    requestOptions = when (transformation) {
        is Transformation.CenterCrop -> requestOptions.centerCrop()
        is Transformation.Circle -> requestOptions.circleCrop()
        else -> requestOptions // Do nothing
    }

    Glide.with(context)
            .load(imageResource)
            .apply(requestOptions)
            .into(this)
}

@Deprecated("Deprecated, use loadImageResource with parameters instead",
        replaceWith = ReplaceWith("loadImageResource(imageResource, transformation = Transformation.CenterCrop)"))
fun ImageView.loadImageResourceCenterCrop(imageResource: String?) {
   loadImageResource(imageResource, transformation =  Transformation.CenterCrop)
}

@Deprecated("Deprecated, use loadImageResource with parameters instead",
        replaceWith = ReplaceWith("loadImageResource(imageResource, transformation = Transformation.CenterCrop)"))
fun ImageView.loadImageResourceCenterCrop(imageResource: Int?) {
    loadImageResource(imageResource, transformation =  Transformation.CenterCrop)
}

@Deprecated("Deprecated, use loadImageResource with parameters instead",
        replaceWith = ReplaceWith("loadImageResource(imageResource, transformation = Transformation.Circle)"))
fun ImageView.loadImageResourceAsCircle(imageResource: String?) {
    loadImageResource(imageResource, transformation =  Transformation.Circle)
}

@Deprecated("Deprecated, use loadImageResource with parameters instead",
        replaceWith = ReplaceWith("loadImageResource(imageResource, transformation = Transformation.Circle)"))
fun ImageView.loadImageResourceAsCircle(imageResource: Int?) {
    loadImageResource(imageResource, transformation =  Transformation.Circle)
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

