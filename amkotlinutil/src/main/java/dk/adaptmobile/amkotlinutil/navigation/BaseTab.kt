package dk.adaptmobile.amkotlinutil.navigation

import android.os.Bundle
import androidx.annotation.IdRes

abstract class BaseTab(@IdRes val id: Int, var initalView: () -> BaseView<*,*>, var state: Bundle? = null)