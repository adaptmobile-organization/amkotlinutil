package dk.adaptmobile.amkotlinutil.navigation

import androidx.multidex.MultiDexApplication
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class BaseApplicationController : MultiDexApplication() {
    val noNetworkSubject: PublishSubject<Boolean> = PublishSubject.create()
    val error: PublishSubject<ErrorMessage> = PublishSubject.create()
    val loading: BehaviorSubject<Boolean> = BehaviorSubject.create()
}
