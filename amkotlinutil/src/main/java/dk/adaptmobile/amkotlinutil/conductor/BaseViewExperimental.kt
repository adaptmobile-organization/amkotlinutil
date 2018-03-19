

package dk.adaptmobile.amkotlinutil.conductor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import dk.adaptmobile.amkotlinutil.extensions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*

/**
 * Created by bjarkeseverinsen on 11/08/2017.
 */

abstract class BaseViewExperimental : RxController(), LayoutContainer {

    private lateinit var unbinder: Unbinder
    private val listener: Controller.LifecycleListener

    override val containerView: View?
        get() = view

    init {
        listener = object : LifecycleListener() {

            override fun postCreateView(controller: Controller, view: View) {
                super.postCreateView(controller, view)
                onViewBound(view)
            }
        }
    }

    protected abstract fun inflateView(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = container.inflate(inflateView(), false)
        unbinder = ButterKnife.bind(this, view)
        addLifecycleListener(listener)
        return view
    }

    protected abstract fun onViewBound(view: View)

    override fun onDetach(view: View) {
        super.onDetach(view)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        unbinder.unbind()
        clearFindViewByIdCache()
    }

}