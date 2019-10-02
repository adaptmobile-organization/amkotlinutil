package dk.adaptmobile.amkotlinutil.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log.d
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.rxlifecycle2.ControllerEvent
import com.bluelinelabs.conductor.rxlifecycle2.RxRestoreViewOnCreateController
import dk.adaptmobile.amkotlinutil.extensions.disposeSafe
import dk.adaptmobile.amkotlinutil.extensions.doOnAndroidMain
import dk.adaptmobile.amkotlinutil.extensions.inflate
import dk.adaptmobile.amkotlinutil.navigation.BaseViewModel.IOutput
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*


@SuppressLint("CheckResult")
abstract class BaseView<T : BaseViewModel<*, T2>, T2: IOutput> : RxRestoreViewOnCreateController(), LayoutContainer {

    open var savedViewState: Bundle? = null
    private val listener: LifecycleListener
    open lateinit var viewModel: T

    override val containerView: View?
        get() = view

    init {
        listener = object : LifecycleListener() {

            override fun preCreateView(controller: Controller) {
                super.preCreateView(controller)

                viewModel = setViewModel()
            }


            override fun onChangeEnd(controller: Controller, changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
                super.onChangeEnd(controller, changeHandler, changeType)

                // Skip when navigating away from view (Prevents events being dispatched twice)
                if (!changeType.isEnter) {
                    return
                }

                viewModel.output
                        .compose(bindUntilEvent(ControllerEvent.DETACH))
                        .doOnAndroidMain()
                        .subscribe(
                                {
                                    if (controller.isAttached) {
                                        handleOutput(it)
                                    }
                                },
                                {
                                    e(this.javaClass.name, "Error in BaseView output: $it")
                                }
                        )
            }

            override fun postCreateView(controller: Controller, view: View) {
                super.postCreateView(controller, view)
                activity?.let { activity ->
                    onViewBound(view, activity)

                    viewModel.loading
                            .compose(bindUntilEvent(ControllerEvent.DESTROY))
                            .doOnAndroidMain()
                            .subscribe {
//                                showLoadingBar(it)
                            }

                    viewModel.error
                            .compose(bindUntilEvent(ControllerEvent.DESTROY))
                            .doOnAndroidMain()
                            .subscribe {
                                //TODO: Handle error
                            }

                    val application = activity.application as BaseApplicationController
                    application.noNetworkSubject
                            .compose(bindUntilEvent(ControllerEvent.DESTROY))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ hasNetwork ->
                                           if (!hasNetwork) {
                                               d(this.javaClass.name, "NoInternetDialog opens")
//                                               NavManager.openModally(Routing.NoInternetDialog())
                                           }
                                       }, {
//                                           NavManager.openModally(Routing.NoInternetDialog())
                                       })
                }
            }
        }
        addLifecycleListener(listener)
    }

    protected abstract fun setViewModel(): T

    protected abstract fun inflateView(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View  {
        this.savedViewState = savedViewState
        return container.inflate(inflateView(), false)
    }

    protected abstract fun onViewBound(view: View, activity: Activity)

    protected abstract fun handleOutput(output: T2)

    open fun callback(data: Any?) {}

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        clearFindViewByIdCache()
        viewModel.disposeBag.disposeSafe()
    }



}

