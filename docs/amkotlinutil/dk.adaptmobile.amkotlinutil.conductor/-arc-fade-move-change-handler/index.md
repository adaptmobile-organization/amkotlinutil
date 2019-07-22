[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.conductor](../index.md) / [ArcFadeMoveChangeHandler](./index.md)

# ArcFadeMoveChangeHandler

`open class ArcFadeMoveChangeHandler : TransitionChangeHandler` [(source)](https://github.com/adaptmobile-organization/amkotlinutil/tree/master/amkotlinutil/amkotlinutil/src/main/java/dk/adaptmobile/amkotlinutil/conductor/ArcFadeMoveChangeHandler.java#L19)

### Constructors

| [&lt;init&gt;](-init-.md) | `ArcFadeMoveChangeHandler()` |

### Functions

| [getTransition](get-transition.md) | `open fun getTransition(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, from: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, to: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, isPush: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Transition`](https://developer.android.com/reference/android/transition/Transition.html) |

### Inheritors

| [SharedElementDelayingChangeHandler](../-shared-element-delaying-change-handler/index.md) | `open class SharedElementDelayingChangeHandler : `[`ArcFadeMoveChangeHandler`](./index.md)<br>A TransitionChangeHandler that will wait for views with the passed transition names to be fully laid out before executing. An OnPreDrawListener will be added to the "to" view, then to all of its subviews that match the transaction names we're interested in. Once all of the views are fully ready, the "to" view is set to invisible so that it'll fade in nicely, and the views that we want to use as shared elements are removed from their containers, then immediately re-added within the beginDelayedTransition call so the system picks them up as shared elements. |

