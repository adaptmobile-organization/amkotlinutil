package dk.adaptmobile.amkotlinutil.navigation

import androidx.multidex.MultiDexApplication
import io.reactivex.subjects.PublishSubject

open class BaseApplicationController : MultiDexApplication() {
    val noNetworkSubject: PublishSubject<Boolean> = PublishSubject.create()
}