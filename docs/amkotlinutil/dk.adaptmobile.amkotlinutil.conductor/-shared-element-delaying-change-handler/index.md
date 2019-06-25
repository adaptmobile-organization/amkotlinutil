[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.conductor](../index.md) / [SharedElementDelayingChangeHandler](index.md)

# SharedElementDelayingChangeHandler

`open class SharedElementDelayingChangeHandler : `[`ArcFadeMoveChangeHandler`](../-arc-fade-move-change-handler/index.md)

A TransitionChangeHandler that will wait for views with the passed transition names to be fully laid out before executing. An OnPreDrawListener will be added to the "to" view, then to all of its subviews that match the transaction names we're interested in. Once all of the views are fully ready, the "to" view is set to invisible so that it'll fade in nicely, and the views that we want to use as shared elements are removed from their containers, then immediately re-added within the beginDelayedTransition call so the system picks them up as shared elements.

### Constructors

| [&lt;init&gt;](-init-.md) | `SharedElementDelayingChangeHandler()`<br>`SharedElementDelayingChangeHandler(waitForTransitionNames: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>)` |

### Functions

| [executePropertyChanges](execute-property-changes.md) | `open fun executePropertyChanges(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, from: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, to: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, transition: `[`Transition`](https://developer.android.com/reference/android/transition/Transition.html)`?, isPush: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAbortPush](on-abort-push.md) | `open fun onAbortPush(newHandler: ControllerChangeHandler, newTop: Controller?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [prepareForTransition](prepare-for-transition.md) | `open fun prepareForTransition(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, from: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, to: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, transition: `[`Transition`](https://developer.android.com/reference/android/transition/Transition.html)`, isPush: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, onTransitionPreparedListener: OnTransitionPreparedListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [restoreFromBundle](restore-from-bundle.md) | `open fun restoreFromBundle(bundle: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [saveToBundle](save-to-bundle.md) | `open fun saveToBundle(bundle: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| [getTransition](../-arc-fade-move-change-handler/get-transition.md) | `open fun getTransition(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, from: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, to: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, isPush: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Transition`](https://developer.android.com/reference/android/transition/Transition.html) |

