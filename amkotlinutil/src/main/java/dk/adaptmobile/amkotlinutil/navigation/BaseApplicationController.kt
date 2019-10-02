package dk.adaptmobile.amkotlinutil.navigation

import io.reactivex.subjects.PublishSubject

class BaseApplicationController {
    val noNetworkSubject: PublishSubject<Boolean> = PublishSubject.create()
}