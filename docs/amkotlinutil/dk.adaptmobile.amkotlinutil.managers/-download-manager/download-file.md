[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.managers](../index.md) / [DownloadManager](index.md) / [downloadFile](./download-file.md)

# downloadFile

`fun downloadFile(url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, downloadRequest: (`[`Request`](https://developer.android.com/reference/android/app/DownloadManager/Request.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null, onComplete: ((uri: `[`Uri`](https://developer.android.com/reference/android/net/Uri.html)`, mimeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/adaptmobile-organization/amkotlinutil/tree/master/amkotlinutil/amkotlinutil/src/main/java/dk/adaptmobile/amkotlinutil/managers/DownloadManager.kt#L37)

This downloads a file using the build in download manager
When the file is downloaded the onComplete callback will be called
With the appropriate file URI and mimeType

