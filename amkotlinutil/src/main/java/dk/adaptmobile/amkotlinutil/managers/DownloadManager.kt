package dk.adaptmobile.amkotlinutil.managers

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import androidx.annotation.RequiresPermission

/**
 * @author Jason Kelly <jason@adaptagency.com>
 */
object DownloadManager {

    /**
     * This downloads a file using the build in download manager
     * When the file is downloaded the onComplete callback will be called
     * With the appropriate file URI and mimeType
     */
    @RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun downloadFile(activity: Activity, url: String, downloadRequest: (DownloadManager.Request.() -> Unit)? = null, onComplete: ((uri: Uri, mimeType: String) -> Unit)? = null) {
        val downloadManager = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(url))

        downloadRequest?.let { request.it() }
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, url)
        request.setVisibleInDownloadsUi(true)

        // Start download
        val downloadId = downloadManager.enqueue(request)
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

                if (downloadId == id) {
                    onComplete?.invoke(downloadManager.getUriForDownloadedFile(downloadId), downloadManager.getMimeTypeForDownloadedFile(downloadId))

                    // Unregister when download finishes
                    activity.unregisterReceiver(this)
                }
            }
        }

        activity.registerReceiver(broadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}
