package dk.adaptmobile.amkotlinutil.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

typealias DisposeBag = CompositeDisposable

fun DisposeBag.disposeSafe() {
    if (!isDisposed) dispose()
}

fun <T> Observable<T>.doOnAndroidMain(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.doOnIO(): Observable<T> {
    return this.observeOn(Schedulers.io())
}

fun <T> Observable<T>.doOnComputation(): Observable<T> {
    return this.observeOn(Schedulers.computation())
}

fun <T> Observable<T>.doOnNewThread(): Observable<T> {
    return this.observeOn(Schedulers.newThread())
}

fun <T> Observable<T>.subscribeOnAndroidMain(): Observable<T> {
    return this.subscribeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.subscribeOnComputation(): Observable<T> {
    return this.subscribeOn(Schedulers.computation())
}

fun <T> Observable<T>.subscribeOnIo(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.subscribeOnNewThread(): Observable<T> {
    return this.subscribeOn(Schedulers.newThread())
}

val <T> BehaviorSubject<T>.requireValue: T
    get() = value ?: throw IllegalStateException("Value must be set before calling this method")

fun <T> BehaviorSubject<MutableList<T>>.add(value: T) {
    val list = requireValue
    list.add(value)
    onNext(list)
}

fun <T> BehaviorSubject<MutableList<T>>.addAll(value: List<T>) {
    val list = requireValue
    list.addAll(value)
    onNext(list)
}