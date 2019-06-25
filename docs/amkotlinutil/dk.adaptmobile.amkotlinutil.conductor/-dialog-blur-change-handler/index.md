[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.conductor](../index.md) / [DialogBlurChangeHandler](index.md)

# DialogBlurChangeHandler

`class DialogBlurChangeHandler : TransitionChangeHandler`

### Constructors

| [&lt;init&gt;](-init-.md) | `DialogBlurChangeHandler(removesFromViewOnPush: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, radius: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 25, sampling: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 2)` |

### Properties

| [radius](radius.md) | `val radius: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [removesFromViewOnPush](removes-from-view-on-push.md) | `val removesFromViewOnPush: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [sampling](sampling.md) | `val sampling: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| [getTransition](get-transition.md) | `fun getTransition(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, from: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, to: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, isPush: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Transition`](https://developer.android.com/reference/android/transition/Transition.html) |
| [removesFromViewOnPush](removes-from-view-on-push.md) | `fun removesFromViewOnPush(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Extension Properties

| [TAG](../../dk.adaptmobile.amkotlinutil.extensions/kotlin.-any/-t-a-g.md) | `val `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`.TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Created by bjarkeseverinsen on 27/09/2017. |

### Extension Functions

| [registerEventBus](../../dk.adaptmobile.amkotlinutil.extensions/register-event-bus.md) | `fun <T> `[`T`](../../dk.adaptmobile.amkotlinutil.extensions/register-event-bus.md#T)`.registerEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [unRegisterEventBus](../../dk.adaptmobile.amkotlinutil.extensions/un-register-event-bus.md) | `fun <T> `[`T`](../../dk.adaptmobile.amkotlinutil.extensions/un-register-event-bus.md#T)`.unRegisterEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

