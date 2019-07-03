[amkotlinutil](../index.md) / [dk.adaptmobile.amkotlinutil.extensions](./index.md)

## Package dk.adaptmobile.amkotlinutil.extensions

### Types

| [AnimationType](-animation-type/index.md) | `sealed class AnimationType` |
| [SoftInputMode](-soft-input-mode/index.md) | `sealed class SoftInputMode`<br>Created by christiansteffensen on 05/06/2017. |
| [Transformation](-transformation/index.md) | `sealed class Transformation`<br>Created by christiansteffensen on 05/06/2017. |

### Type Aliases

| [DisposeBag](-dispose-bag.md) | `typealias DisposeBag = CompositeDisposable` |

### Extensions for External Classes

| [android.animation.AnimatorSet](android.animation.-animator-set/index.md) |  |
| [android.app.Activity](android.app.-activity/index.md) |  |
| [android.app.Dialog](android.app.-dialog/index.md) |  |
| [android.content.Context](android.content.-context/index.md) |  |
| [android.graphics.Bitmap](android.graphics.-bitmap/index.md) |  |
| [android.os.Handler](android.os.-handler/index.md) |  |
| [android.transition.TransitionSet](android.transition.-transition-set/index.md) |  |
| [android.view.View](android.view.-view/index.md) |  |
| [android.view.ViewGroup](android.view.-view-group/index.md) |  |
| [android.view.ViewPropertyAnimator](android.view.-view-property-animator/index.md) |  |
| [android.webkit.WebView](android.webkit.-web-view/index.md) |  |
| [android.widget.EditText](android.widget.-edit-text/index.md) |  |
| [android.widget.ImageView](android.widget.-image-view/index.md) |  |
| [android.widget.TextView](android.widget.-text-view/index.md) |  |
| [androidx.recyclerview.widget.RecyclerView](androidx.recyclerview.widget.-recycler-view/index.md) |  |
| [androidx.recyclerview.widget.RecyclerView.ViewHolder](androidx.recyclerview.widget.-recycler-view.-view-holder/index.md) |  |
| [androidx.viewpager.widget.ViewPager](androidx.viewpager.widget.-view-pager/index.md) |  |
| [com.bluelinelabs.conductor.Controller](com.bluelinelabs.conductor.-controller/index.md) |  |
| [com.bluelinelabs.conductor.Router](com.bluelinelabs.conductor.-router/index.md) |  |
| [com.google.android.material.textfield.TextInputLayout](com.google.android.material.textfield.-text-input-layout/index.md) |  |
| [com.xwray.groupie.GroupAdapter](com.xwray.groupie.-group-adapter/index.md) |  |
| [io.reactivex.disposables.CompositeDisposable](io.reactivex.disposables.-composite-disposable/index.md) |  |
| [io.reactivex.Observable](io.reactivex.-observable/index.md) |  |
| [io.reactivex.subjects.BehaviorSubject](io.reactivex.subjects.-behavior-subject/index.md) |  |
| [java.util.Date](java.util.-date/index.md) |  |
| [kotlin.Any](kotlin.-any/index.md) |  |
| [kotlin.Boolean](kotlin.-boolean/index.md) |  |
| [kotlin.ByteArray](kotlin.-byte-array/index.md) |  |
| [kotlin.CharSequence](kotlin.-char-sequence/index.md) |  |
| [kotlin.collections.List](kotlin.collections.-list/index.md) |  |
| [kotlin.collections.MutableList](kotlin.collections.-mutable-list/index.md) |  |
| [kotlin.Double](kotlin.-double/index.md) |  |
| [kotlin.Float](kotlin.-float/index.md) |  |
| [kotlin.Int](kotlin.-int/index.md) |  |
| [kotlin.Long](kotlin.-long/index.md) |  |
| [kotlin.Number](kotlin.-number/index.md) |  |
| [kotlin.String](kotlin.-string/index.md) |  |
| [okhttp3.Response](okhttp3.-response/index.md) |  |
| [org.greenrobot.eventbus.EventBus](org.greenrobot.eventbus.-event-bus/index.md) |  |

### Functions

| [afterMeasured](after-measured.md) | `fun <T : `[`View`](https://developer.android.com/reference/android/view/View.html)`> `[`T`](after-measured.md#T)`.afterMeasured(f: `[`T`](after-measured.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [danishLocale](danish-locale.md) | `fun danishLocale(): `[`Locale`](https://developer.android.com/reference/java/util/Locale.html) |
| [registerEventBus](register-event-bus.md) | `fun <T> `[`T`](register-event-bus.md#T)`.registerEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [safeLet](safe-let.md) | `fun <T1 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T2 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, R : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> safeLet(p1: `[`T1`](safe-let.md#T1)`?, p2: `[`T2`](safe-let.md#T2)`?, block: (`[`T1`](safe-let.md#T1)`, `[`T2`](safe-let.md#T2)`) -> `[`R`](safe-let.md#R)`?): `[`R`](safe-let.md#R)`?`<br>`fun <T1 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T2 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T3 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, R : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> safeLet(p1: `[`T1`](safe-let.md#T1)`?, p2: `[`T2`](safe-let.md#T2)`?, p3: `[`T3`](safe-let.md#T3)`?, block: (`[`T1`](safe-let.md#T1)`, `[`T2`](safe-let.md#T2)`, `[`T3`](safe-let.md#T3)`) -> `[`R`](safe-let.md#R)`?): `[`R`](safe-let.md#R)`?`<br>`fun <T1 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T2 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T3 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T4 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, R : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> safeLet(p1: `[`T1`](safe-let.md#T1)`?, p2: `[`T2`](safe-let.md#T2)`?, p3: `[`T3`](safe-let.md#T3)`?, p4: `[`T4`](safe-let.md#T4)`?, block: (`[`T1`](safe-let.md#T1)`, `[`T2`](safe-let.md#T2)`, `[`T3`](safe-let.md#T3)`, `[`T4`](safe-let.md#T4)`) -> `[`R`](safe-let.md#R)`?): `[`R`](safe-let.md#R)`?`<br>`fun <T1 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T2 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T3 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T4 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, T5 : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, R : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> safeLet(p1: `[`T1`](safe-let.md#T1)`?, p2: `[`T2`](safe-let.md#T2)`?, p3: `[`T3`](safe-let.md#T3)`?, p4: `[`T4`](safe-let.md#T4)`?, p5: `[`T5`](safe-let.md#T5)`?, block: (`[`T1`](safe-let.md#T1)`, `[`T2`](safe-let.md#T2)`, `[`T3`](safe-let.md#T3)`, `[`T4`](safe-let.md#T4)`, `[`T5`](safe-let.md#T5)`) -> `[`R`](safe-let.md#R)`?): `[`R`](safe-let.md#R)`?` |
| [unRegisterEventBus](un-register-event-bus.md) | `fun <T> `[`T`](un-register-event-bus.md#T)`.unRegisterEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [wait](wait.md) | `fun wait(delayMillis: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, mainThread: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, function: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`PostDelay`](../dk.adaptmobile.amkotlinutil.model/-post-delay/index.md) |
| [waitRx](wait-rx.md) | `fun waitRx(delayMillis: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, function: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

