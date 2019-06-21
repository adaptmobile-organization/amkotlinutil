package dk.adaptmobile.amkotlinutil.extensions

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.Base64
import android.widget.ImageView
import androidx.annotation.ColorRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 * Created by christiansteffensen on 05/06/2017.
 */
sealed class Transformation {
    object CenterCrop : Transformation()
    object Circle : Transformation()
}

fun ImageView.loadImageResource(imageResource: String?, skipMemoryCache: Boolean = false, transformation: Transformation? = null, imageLoadedCallback: (() -> Unit)? = null) {
    imageResource?.let {
        loadImage(it, skipMemoryCache, transformation, imageLoadedCallback)
    }
}

fun ImageView.loadImageResource(imageResource: Int?, skipMemoryCache: Boolean = false, transformation: Transformation? = null, imageLoadedCallback: (() -> Unit)? = null) {
    imageResource?.let {
        loadImage(it, skipMemoryCache, transformation, imageLoadedCallback)
    }
}

fun ImageView.loadImageResource(imageResource: Bitmap?, skipMemoryCache: Boolean = false, transformation: Transformation? = null, imageLoadedCallback: (() -> Unit)? = null) {
    imageResource?.let {
        loadImage(it, skipMemoryCache, transformation, imageLoadedCallback)
    }
}

fun ImageView.loadImageResource(imageResource: Drawable?, skipMemoryCache: Boolean = false, transformation: Transformation? = null, imageLoadedCallback: (() -> Unit)? = null) {
    imageResource?.let {
        loadImage(it, skipMemoryCache, transformation, imageLoadedCallback)
    }
}

private fun ImageView.loadImage(imageResource: Any, skipMemoryCache: Boolean, transformation: Transformation?, imageLoadedCallback: (() -> Unit)?) {
    val glide = Glide.with(context)
            .load(imageResource)
            .skipMemoryCache(skipMemoryCache)

    when (transformation) {
        is Transformation.CenterCrop -> glide.centerCrop()
        is Transformation.Circle -> glide.circleCrop()
    }

    imageLoadedCallback?.let {
        glide.addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean = false

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                imageLoadedCallback()
                return false
            }

        })
    }

    glide.into(this)
}

@Deprecated("Deprecated, use loadImageResource with parameters instead",
        replaceWith = ReplaceWith("loadImageResource(imageResource, transformation = Transformation.CenterCrop)"))
fun ImageView.loadImageResourceCenterCrop(imageResource: String?) {
    loadImageResource(imageResource, transformation = Transformation.CenterCrop)
}

@Deprecated("Deprecated, use loadImageResource with parameters instead",
        replaceWith = ReplaceWith("loadImageResource(imageResource, transformation = Transformation.CenterCrop)"))
fun ImageView.loadImageResourceCenterCrop(imageResource: Int?) {
    loadImageResource(imageResource, transformation = Transformation.CenterCrop)
}

@Deprecated("Deprecated, use loadImageResource with parameters instead",
        replaceWith = ReplaceWith("loadImageResource(imageResource, transformation = Transformation.Circle)"))
fun ImageView.loadImageResourceAsCircle(imageResource: String?) {
    loadImageResource(imageResource, transformation = Transformation.Circle)
}

@Deprecated("Deprecated, use loadImageResource with parameters instead",
        replaceWith = ReplaceWith("loadImageResource(imageResource, transformation = Transformation.Circle)"))
fun ImageView.loadImageResourceAsCircle(imageResource: Int?) {
    loadImageResource(imageResource, transformation = Transformation.Circle)
}

fun ImageView.setTint(@ColorRes colorRes: Int, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_OVER) {
    this.setColorFilter(context.getColorCompat(colorRes), mode)
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

