[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.util](../index.md) / [PreferenceHelper](index.md)

# PreferenceHelper

`object PreferenceHelper`

Created by bjarkeseverinsen on 20/08/2017.

### Functions

| [customPrefs](custom-prefs.md) | `fun customPrefs(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html) |
| [defaultPrefs](default-prefs.md) | `fun defaultPrefs(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html) |
| [edit](edit.md) | `fun `[`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html)`.edit(operation: (`[`Editor`](https://developer.android.com/reference/android/content/SharedPreferences/Editor.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [get](get.md) | `operator fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> `[`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html)`.get(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, defaultValue: `[`T`](get.md#T)`? = null): `[`T`](get.md#T)`?`<br>finds value on given key. [T](get.md#T) is the type of value |
| [set](set.md) | `operator fun `[`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html)`.set(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key](set.md#dk.adaptmobile.amkotlinutil.util.PreferenceHelper$set(android.content.SharedPreferences, kotlin.String, kotlin.Any)/key) |

### Extension Properties

| [TAG](../../dk.adaptmobile.amkotlinutil.extensions/kotlin.-any/-t-a-g.md) | `val `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`.TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Created by bjarkeseverinsen on 27/09/2017. |

### Extension Functions

| [registerEventBus](../../dk.adaptmobile.amkotlinutil.extensions/register-event-bus.md) | `fun <T> `[`T`](../../dk.adaptmobile.amkotlinutil.extensions/register-event-bus.md#T)`.registerEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [unRegisterEventBus](../../dk.adaptmobile.amkotlinutil.extensions/un-register-event-bus.md) | `fun <T> `[`T`](../../dk.adaptmobile.amkotlinutil.extensions/un-register-event-bus.md#T)`.unRegisterEventBus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

