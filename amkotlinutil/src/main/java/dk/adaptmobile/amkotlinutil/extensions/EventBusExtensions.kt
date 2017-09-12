package dk.adaptmobile.amkotlinutil.extensions

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

fun <T> T.registerEventBus() {
    if (!EventBus.getDefault().isRegistered(this)) {
        EventBus.getDefault().register(this)
    }
}

fun <T> T.unRegisterEventBus() {
    if (EventBus.getDefault().isRegistered(this)) {
        EventBus.getDefault().unregister(this)
    }
}