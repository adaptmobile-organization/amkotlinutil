package dk.adaptmobile.amkotlinutil.navigation

import android.util.Log.e
import dk.adaptmobile.amkotlinutil.extensions.subscribeOnAndroidMain
import dk.adaptmobile.amkotlinutil.navigation.BaseViewModel.IInput
import dk.adaptmobile.amkotlinutil.navigation.BaseViewModel.IOutput
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.ReplaySubject

abstract class BaseViewModel<T : IInput, T2: IOutput> {
    val disposeBag: CompositeDisposable = CompositeDisposable()
    val output: ReplaySubject<T2> = ReplaySubject.create()
    val input: BehaviorSubject<T> = BehaviorSubject.create()

    open class IOutput
    open class IInput

    protected abstract fun handleInput(input: T)

    init {
        input
                .observeOn(Schedulers.computation())
                .subscribe(
                        { handleInput(it) },
                        { e(this.javaClass.name, "Error subscring to input: $it") }
                )
                .addTo(disposeBag)
    }

    protected fun <T> subscribeToInput(observable: Observable<T>, callback: (value: T) -> Unit) {
        observable
                .subscribeOnAndroidMain()
                .subscribe(
                        {
                            callback(it)
                        },
                        {
                            e(this.javaClass.name, "Error: $it")
                        }
                ).addTo(disposeBag)
    }

    protected fun <T> subscribeToInput(observable: Single<T>, callback: (value: T) -> Unit) {
        observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            callback(it)
                        },
                        {
                            e(this.javaClass.name, "Error: $it")
                        }
                ).addTo(disposeBag)
    }
}