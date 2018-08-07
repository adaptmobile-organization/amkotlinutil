package dk.adaptmobile.amkotlinutil.conductor

import android.os.Parcelable
import com.bluelinelabs.conductor.Controller
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * An abstract argument delegate that uses the property to infer the key name for the bundle.
 */
class ArgumentDelegate<T> : ReadWriteProperty<Controller, T> {

    private var value: T? = null

    override fun getValue(thisRef: Controller, property: KProperty<*>): T {
        if (value == null) {
            val key = property.name
            value = thisRef.args.get(key) as T
        }

        if (value == null) {
            value = thisRef.targetController as T
        }

        return value ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override fun setValue(thisRef: Controller, property: KProperty<*>, value: T) {
        val bundle = thisRef.args
        val key = property.name

        when (value) {
            is Controller -> thisRef.targetController = value
            is Parcelable -> bundle.putParcelable(key, value)
            is Serializable -> bundle.putSerializable(key, value)
            is String -> bundle.putString(key, value)
            is Int -> bundle.putInt(key, value)
            is Boolean -> bundle.putBoolean(key, value)
            is Float -> bundle.putFloat(key, value)
            is Long -> bundle.putLong(key, value)
            is Double -> bundle.putDouble(key, value)
            is Byte -> bundle.putByte(key, value)
            is ByteArray -> bundle.putByteArray(key, value)
            is Char -> bundle.putChar(key, value)
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

}