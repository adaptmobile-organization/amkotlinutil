package dk.adaptmobile.amkotlinutil.extensions

import android.annotation.SuppressLint
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.ajalt.timberkt.e
import io.reactivex.rxjava3.core.Observable
import java.io.File

/**
 * Created by bjarkeseverinsen on 27/09/2017.
 */

/**
 * Hides all the views passed in the arguments
 */
fun Context.hideViews(vararg views: View) = views.forEach { it.visibility = View.GONE }

/**
 * Shows all the views passed in the arguments
 */
fun Context.showViews(vararg views: View) = views.forEach { it.visibility = View.VISIBLE }

fun Context.getColorCompat(resId: Int): Int = ContextCompat.getColor(this, resId)

fun Context.getDrawableCompat(resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Context.getDimension(resId: Int): Float = this.resources.getDimension(resId)

fun Int.dpToPixels(context: Context?): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context?.resources?.displayMetrics)
}

fun Float.dpToPixels(context: Context?): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context?.resources?.displayMetrics)
}

fun Int.dp(context: Context?): Float {
    return this.dpToPixels(context)
}

fun Float.dp(context: Context?): Float {
    return this.dpToPixels(context)
}

fun Int.convertPixelsToDp(context: Context): Int {
    return (this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Float.convertPixelsToDp(context: Context): Int {
    return (this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Context.unRegisterReceiverSafe(broadcastReceiver: BroadcastReceiver) {
    // needs to be in try catch in order to avoid crashing on Samsung Lollipop devices https://issuetracker.google.com/issues/37001269#c3
    try {
        this.unregisterReceiver(broadcastReceiver)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}

fun Context.registerReceiverSafe(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) {
    // needs to be in try catch in order to avoid crashing on Samsung Lollipop devices https://issuetracker.google.com/issues/37001269#c3
    try {
        this.registerReceiver(broadcastReceiver, intentFilter)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}

fun Context?.openInBrowser(url: String?) {
    if (url != null && url.isNotEmpty()) {
        val page = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, page)
        if (intent.resolveActivity(this?.packageManager) != null) {
            this?.startActivity(intent)
        }
    }
}

fun Context.getFontCompat(fontRes: Int): Typeface? {
    return ResourcesCompat.getFont(this, fontRes)
}

fun Context?.composeEmail(addresses: Array<String>, subject: String, text: String) {
    this?.let {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}

fun Context.goToPlayStoreDetails() {
    val uri = Uri.parse("market://details?id=${this.getProductionApplicationId()}")
    val linkToMarket = Intent(Intent.ACTION_VIEW, uri)
    try {
        startActivity(linkToMarket)
    } catch (ex: ActivityNotFoundException) {
        e { "Unable to find market app: $ex" }
    }
}

fun Context.getProductionApplicationId(): String {
    val applicationId = packageName
    return when {
        applicationId.contains(".stage") -> applicationId.dropLast(6)
        applicationId.contains(".debug") -> applicationId.dropLast(6)
        else -> applicationId
    }
}

fun Context?.dial(number: String) {
    this?.let {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        this.startActivity(intent)
    }
}

fun Context?.openGoogleMaps(query: String, placeId: String) {
    val queryEncoded = Uri.encode(query)
    val gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$queryEncoded&query_place_id=$placeId")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(this?.packageManager) != null) {
        this?.startActivity(mapIntent)
    }
}

@SuppressLint("MissingPermission")
fun Context.isOnline(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    val isOffline = networkInfo == null || !networkInfo.isConnectedOrConnecting
    return !isOffline
}

fun Context.cacheImage(url: String): Observable<Boolean> {
    return Observable.create {

        if (url.isEmpty()) {
            it.onNext(false)
        } else {
            Glide.with(applicationContext)
                    .downloadOnly()
                    .load(url)
                    .listener(object : RequestListener<File> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<File>?, isFirstResource: Boolean): Boolean {
                            if (e != null) {
                                it.onNext(false)
                            }
                            return false
                        }

                        override fun onResourceReady(resource: File?, model: Any?, target: Target<File>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            it.onNext(true)
                            return false
                        }
                    })
                    .submit()
        }
    }
}

fun Context.areNotificationsEnabled(): Boolean {
    return NotificationManagerCompat.from(this).areNotificationsEnabled()
}

/**
 * Loads String from json by assets filename.
 * The file needs to be in the main -> assets folder
 */
fun Context?.loadJSONFromAssetsFileName(fileName: String): String {
    this ?: return ""
    return this.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}

fun Context?.isGmailInstalled(): Boolean {
    this ?: return false
    return this.packageManager.getInstalledApplications(PackageManager.GET_META_DATA).firstOrNull { it.packageName == "com.google.android.gm" } != null
}

fun Context?.navigateToGmailInbox() {
    this ?: return
    val packageManager = this.packageManager
    val gmailIntent = packageManager?.getLaunchIntentForPackage("com.google.android.gm")
    gmailIntent?.addCategory(Intent.CATEGORY_LAUNCHER)
    this.startActivity(gmailIntent)
}

fun Context?.navigateToAndroidAppSettings() {
    this ?: return
    val intent = Intent().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            putExtra(Settings.EXTRA_APP_PACKAGE, this@navigateToAndroidAppSettings.packageName)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        } else {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            addCategory(Intent.CATEGORY_DEFAULT)
            data = Uri.parse("package:" + this@navigateToAndroidAppSettings.packageName)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }
    this.startActivity(intent)
}
