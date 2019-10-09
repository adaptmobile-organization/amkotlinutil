package dk.adaptmobile.amkotlinutil.util

import io.reactivex.subjects.PublishSubject

interface BaseDependencies{
    val noNetworkSubject: PublishSubject<Boolean>
    val loadingSubject: PublishSubject<Boolean>
}