package dk.adaptmobile.amkotlinutil.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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