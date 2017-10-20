package dk.adaptmobile.amkotlinutil.util;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;

public class AMBundleBuilder {

    private final Bundle bundle;

    public AMBundleBuilder(Bundle bundle) {
        this.bundle = bundle;
    }

    public AMBundleBuilder putAll(Bundle bundle) {
        this.bundle.putAll(bundle);
        return this;
    }

    public AMBundleBuilder putBoolean(String key, boolean value) {
        bundle.putBoolean(key, value);
        return this;
    }

    public AMBundleBuilder putBooleanArray(String key, boolean[] value) {
        bundle.putBooleanArray(key, value);
        return this;
    }

    public AMBundleBuilder putDouble(String key, double value) {
        bundle.putDouble(key, value);
        return this;
    }

    public AMBundleBuilder putDoubleArray(String key, double[] value) {
        bundle.putDoubleArray(key, value);
        return this;
    }

    public AMBundleBuilder putLong(String key, long value) {
        bundle.putLong(key, value);
        return this;
    }

    public AMBundleBuilder putLongArray(String key, long[] value) {
        bundle.putLongArray(key, value);
        return this;
    }

    public AMBundleBuilder putString(String key, String value) {
        bundle.putString(key, value);
        return this;
    }

    public AMBundleBuilder putStringArray(String key, String[] value) {
        bundle.putStringArray(key, value);
        return this;
    }

    public AMBundleBuilder putBundle(String key, Bundle value) {
        bundle.putBundle(key, value);
        return this;
    }

    public AMBundleBuilder putByte(String key, byte value) {
        bundle.putByte(key, value);
        return this;
    }

    public AMBundleBuilder putByteArray(String key, byte[] value) {
        bundle.putByteArray(key, value);
        return this;
    }

    public AMBundleBuilder putChar(String key, char value) {
        bundle.putChar(key, value);
        return this;
    }

    public AMBundleBuilder putCharArray(String key, char[] value) {
        bundle.putCharArray(key, value);
        return this;
    }

    public AMBundleBuilder putCharSequence(String key, CharSequence value) {
        bundle.putCharSequence(key, value);
        return this;
    }

    public AMBundleBuilder putCharSequenceArray(String key, CharSequence[] value) {
        bundle.putCharSequenceArray(key, value);
        return this;
    }

    public AMBundleBuilder putCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
        bundle.putCharSequenceArrayList(key, value);
        return this;
    }

    public AMBundleBuilder putInt(String key, int value) {
        bundle.putInt(key, value);
        return this;
    }

    public AMBundleBuilder putIntArray(String key, int[] value) {
        bundle.putIntArray(key, value);
        return this;
    }

    public AMBundleBuilder putFloat(String key, float value) {
        bundle.putFloat(key, value);
        return this;
    }

    public AMBundleBuilder putFloatArray(String key, float[] value) {
        bundle.putFloatArray(key, value);
        return this;
    }

    public AMBundleBuilder putIntegerArrayList(String key, ArrayList<Integer> value) {
        bundle.putIntegerArrayList(key, value);
        return this;
    }

    public AMBundleBuilder putParcelable(String key, Parcelable value) {
        bundle.putParcelable(key, value);
        return this;
    }

    public AMBundleBuilder putParcelableArray(String key, Parcelable[] value) {
        bundle.putParcelableArray(key, value);
        return this;
    }

    public AMBundleBuilder putParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
        bundle.putParcelableArrayList(key, value);
        return this;
    }

    public AMBundleBuilder putSerializable(String key, Serializable value) {
        bundle.putSerializable(key, value);
        return this;
    }

    public AMBundleBuilder putShort(String key, short value) {
        bundle.putShort(key, value);
        return this;
    }

    public AMBundleBuilder putShortArray(String key, short[] value) {
        bundle.putShortArray(key, value);
        return this;
    }

    public AMBundleBuilder putSparseParcelableArray(String key, SparseArray<? extends Parcelable> value) {
        bundle.putSparseParcelableArray(key, value);
        return this;
    }

    public AMBundleBuilder putStringArrayList(String key, ArrayList<String> value) {
        bundle.putStringArrayList(key, value);
        return this;
    }

    public Bundle build() {
        return bundle;
    }

}