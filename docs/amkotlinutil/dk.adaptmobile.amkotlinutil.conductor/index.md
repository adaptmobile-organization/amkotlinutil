[amkotlinutil](../index.md) / [dk.adaptmobile.amkotlinutil.conductor](./index.md)

## Package dk.adaptmobile.amkotlinutil.conductor

### Types

| [ArcFadeMoveChangeHandler](-arc-fade-move-change-handler/index.md) | `open class ArcFadeMoveChangeHandler : TransitionChangeHandler` |
| [ArcFadeMoveChangeHandlerCompat](-arc-fade-move-change-handler-compat/index.md) | `open class ArcFadeMoveChangeHandlerCompat : TransitionChangeHandlerCompat` |
| [ArgumentDelegate](-argument-delegate/index.md) | `class ArgumentDelegate<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<Controller, `[`T`](-argument-delegate/index.md#T)`>`<br>An abstract argument delegate that uses the property to infer the key name for the bundle. |
| [ArgumentDelegateNullable](-argument-delegate-nullable/index.md) | `class ArgumentDelegateNullable<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<Controller, `[`T`](-argument-delegate-nullable/index.md#T)`?>`<br>An abstract argument delegate that uses the property to infer the key name for the bundle. |
| [CircularRevealChangeHandler](-circular-reveal-change-handler/index.md) | `open class CircularRevealChangeHandler : AnimatorChangeHandler`<br>An ``[`AnimatorChangeHandler`](#) that will perform a circular reveal |
| [CircularRevealChangeHandlerCompat](-circular-reveal-change-handler-compat/index.md) | `open class CircularRevealChangeHandlerCompat : `[`CircularRevealChangeHandler`](-circular-reveal-change-handler/index.md) |
| [DialogBlurChangeHandler](-dialog-blur-change-handler/index.md) | `class DialogBlurChangeHandler : TransitionChangeHandler` |
| [DialogFadeChangeHandler](-dialog-fade-change-handler/index.md) | `class DialogFadeChangeHandler : TransitionChangeHandler` |
| [FlipChangeHandler](-flip-change-handler/index.md) | `open class FlipChangeHandler : AnimatorChangeHandler` |
| [Scale](-scale/index.md) | `open class Scale : `[`Visibility`](https://developer.android.com/reference/android/transition/Visibility.html) |
| [ScaleFadeChangeHandler](-scale-fade-change-handler/index.md) | `open class ScaleFadeChangeHandler : AnimatorChangeHandler` |
| [SharedElementDelayingChangeHandler](-shared-element-delaying-change-handler/index.md) | `open class SharedElementDelayingChangeHandler : `[`ArcFadeMoveChangeHandler`](-arc-fade-move-change-handler/index.md)<br>A TransitionChangeHandler that will wait for views with the passed transition names to be fully laid out before executing. An OnPreDrawListener will be added to the "to" view, then to all of its subviews that match the transaction names we're interested in. Once all of the views are fully ready, the "to" view is set to invisible so that it'll fade in nicely, and the views that we want to use as shared elements are removed from their containers, then immediately re-added within the beginDelayedTransition call so the system picks them up as shared elements. |

