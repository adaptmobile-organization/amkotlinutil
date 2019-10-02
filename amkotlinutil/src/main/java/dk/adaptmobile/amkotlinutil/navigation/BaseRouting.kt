package dk.adaptmobile.amkotlinutil.navigation

import android.os.Bundle
import dk.adaptmobile.amkotlinutil.extensions.AnimationType

abstract class BaseRouting(val controller: BaseView<*,*>? = null,
                         val animationType: AnimationType = AnimationType.Slide,
                         open val asRoot: Boolean = false,
                         val retain: Boolean = false) {

    abstract class BaseBottomNav : BaseRouting()

    //Generic routing
    class Back(val amount: Int = 1, val data: Any? = null) : BaseRouting()
    object CloseAll : BaseRouting()

    //Tab routing
    class OpenTab(val state: Bundle) : BaseRouting()
    class SetTabRoot(val view: BaseView<*,*>) : BaseRouting()
    class GoToRoot : BaseRouting()
    class MarkTabAsSelected(val menuId: Int) : BaseRouting()
}