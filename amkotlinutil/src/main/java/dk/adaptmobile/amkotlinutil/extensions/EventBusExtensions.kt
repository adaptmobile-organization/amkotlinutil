package dk.adaptmobile.amkotlinutil.extensions

import org.greenrobot.eventbus.EventBus

/**
 * Created by Thomas on 12/06/2017.
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