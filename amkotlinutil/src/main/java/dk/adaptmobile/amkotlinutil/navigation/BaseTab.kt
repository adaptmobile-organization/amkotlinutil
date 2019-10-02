package dk.adaptmobile.amkotlinutil.navigation

import android.os.Bundle
import androidx.annotation.IdRes

interface BaseTabObject {
    fun getInitialView() : BaseView<*, *>
}

abstract class BaseTab(@IdRes val id: Int, var state: Bundle? = null) : BaseTabObject