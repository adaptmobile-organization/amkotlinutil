package dk.brunata.brunata.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.android.appKodein
import dk.adaptmobile.amkotlinutil.util.ViewBinder

/**
 * Created by bjarkeseverinsen on 10/09/2017.
 */

abstract class BaseView : Controller {

    protected constructor()

    protected constructor(args: Bundle) : super(args)

    private lateinit var unbinder: Unbinder

    val lazyKodein: LazyKodein
        get() = LazyKodein(baseActivity.appKodein)

    val baseActivity: Activity
        get() = androidActivityScope.getContext()

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        unbinder = ButterKnife.bind(this, view)
        ViewBinder.setup(this, view)
        onViewBound(view)

        return view
    }

    protected abstract fun onViewBound(view: View)

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        unbinder.unbind()
        ViewBinder.tearDown(this)
    }
}