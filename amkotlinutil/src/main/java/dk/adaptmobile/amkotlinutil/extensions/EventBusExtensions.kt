package dk.adaptmobile.amkotlinutil.extensions

import dk.adaptmobile.amkotlinutil.base.BaseView
import org.greenrobot.eventbus.EventBus

/**
 * Created by Bjarke on 11/08/2017.
 */

fun EventBus.registerMe(subscriber: Any) {
    if (!EventBus.getDefault().isRegistered(subscriber)) {
        EventBus.getDefault().register(subscriber)
    }
}

fun EventBus.unRegisterMe(subscriber: Any) {
    if (EventBus.getDefault().isRegistered(subscriber)) {
        EventBus.getDefault().unregister(subscriber)
    }
}

fun BaseView.registerEventBus() {
    if (!org.greenrobot.eventbus.EventBus.getDefault().isRegistered(this)) {
        org.greenrobot.eventbus.EventBus.getDefault().register(this)
    }
}

fun BaseView.unRegisterEventBus() {
    if (org.greenrobot.eventbus.EventBus.getDefault().isRegistered(this)) {
        org.greenrobot.eventbus.EventBus.getDefault().unregister(this)
    }
}