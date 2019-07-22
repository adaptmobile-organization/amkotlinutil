[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.util](../index.md) / [PreferenceHelper](index.md) / [get](./get.md)

# get

`inline operator fun <reified T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> `[`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html)`.get(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, defaultValue: `[`T`](get.md#T)`? = null): `[`T`](get.md#T)`?` [(source)](https://github.com/adaptmobile-organization/amkotlinutil/tree/master/amkotlinutil/amkotlinutil/src/main/java/dk/adaptmobile/amkotlinutil/util/PreferenceHelper.kt#L45)

finds value on given key.
[T](get.md#T) is the type of value

### Parameters

`defaultValue` - optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue](get.md#dk.adaptmobile.amkotlinutil.util.PreferenceHelper$get(android.content.SharedPreferences, kotlin.String, dk.adaptmobile.amkotlinutil.util.PreferenceHelper.get.T)/defaultValue) is not specified