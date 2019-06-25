[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.managers](../index.md) / [DownloadManager](index.md)

# DownloadManager

`object DownloadManager`

**Author**
Jason Kelly &lt;&gt;

### Functions

| [downloadFile](download-file.md) | `fun downloadFile(url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, downloadRequest: (`[`Request`](https://developer.android.com/reference/android/app/DownloadManager/Request.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null, onComplete: ((uri: `[`Uri`](https://developer.android.com/reference/android/net/Uri.html)`, mimeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)? = null): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This downloads a file using the build in download manager When the file is downloaded the onComplete callback will be called With the appropriate file URI and mimeType |
| [init](init.md) | `fun init(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This will initialize the download manager with the specified application context |

### Extension Properties

| [TAG](../../dk.adaptmobile.amkotlinutil.extensions/kotlin.-any/-t-a-g.md) | `val `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`.TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Created by bjarkeseverinsen on 27/09/2017. |

### Extension Functions

| [registerEventBus](../../dk.adaptmobile.amkotlinutil.extensions/register-event-bus.md) | `fun <T> `[`T`](../../dk.adaptmobile.amkotlinutil.extensions/register-event-bus.md#T)`.registerEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [unRegisterEventBus](../../dk.adaptmobile.amkotlinutil.extensions/un-register-event-bus.md) | `fun <T> `[`T`](../../dk.adaptmobile.amkotlinutil.extensions/un-register-event-bus.md#T)`.unRegisterEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

