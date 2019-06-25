[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.conductor](../index.md) / [ArgumentDelegate](index.md)

# ArgumentDelegate

`class ArgumentDelegate<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<Controller, `[`T`](index.md#T)`>`

An abstract argument delegate that uses the property to infer the key name for the bundle.

### Constructors

| [&lt;init&gt;](-init-.md) | `ArgumentDelegate()`<br>An abstract argument delegate that uses the property to infer the key name for the bundle. |

### Functions

| [getValue](get-value.md) | `fun getValue(thisRef: Controller, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): `[`T`](index.md#T) |
| [setValue](set-value.md) | `fun setValue(thisRef: Controller, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>, value: `[`T`](index.md#T)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extension Properties

| [TAG](../../dk.adaptmobile.amkotlinutil.extensions/kotlin.-any/-t-a-g.md) | `val `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`.TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Created by bjarkeseverinsen on 27/09/2017. |

### Extension Functions

| [registerEventBus](../../dk.adaptmobile.amkotlinutil.extensions/register-event-bus.md) | `fun <T> `[`T`](../../dk.adaptmobile.amkotlinutil.extensions/register-event-bus.md#T)`.registerEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [unRegisterEventBus](../../dk.adaptmobile.amkotlinutil.extensions/un-register-event-bus.md) | `fun <T> `[`T`](../../dk.adaptmobile.amkotlinutil.extensions/un-register-event-bus.md#T)`.unRegisterEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

