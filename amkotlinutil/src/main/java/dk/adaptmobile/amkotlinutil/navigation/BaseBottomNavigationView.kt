package dk.adaptmobile.amkotlinutil.navigation

import android.annotation.SuppressLint
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.rxlifecycle2.ControllerEvent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxbinding3.material.itemSelections
import dk.adaptmobile.amkotlinutil.extensions.*
import io.reactivex.rxkotlin.addTo


@SuppressLint("CheckResult")
abstract class BaseBottomNavigationView<T : BaseViewModel<*, T2>, T2 : BaseViewModel.IOutput> : BaseView<T, T2>() {
    private lateinit var tabRouter: Router

    fun setupRouting(tabContainer: ChangeHandlerFrameLayout, bottomNavigation: BottomNavigationView) {

        tabRouter = getChildRouter(tabContainer)

        bottomNavigation.itemSelections()
                .compose(bindUntilEvent(ControllerEvent.DESTROY))
                .subscribe {
                    NavManager.tabSelected(it)
                }

        NavManager.tabRouting
                .doOnAndroidMain()
                .subscribe {
                    when (it) {
                        is BaseRouting.GoToRoot -> tabRouter.popToRoot()
                        is BaseRouting.SetTabRoot -> {
                            NavManager.setCurrentTabState(tabRouter.getStateAsBundle())
                            tabRouter.pushView(it.view, AnimationType.None, asRoot = true)
                        }
                        is BaseRouting.OpenTab -> tabRouter.restoreState(it.state)
                        is BaseRouting.Back -> tabRouter.goBack(it.amount, it.data)
                        is BaseRouting.MarkTabAsSelected -> bottomNavigation.selectedItemId = it.menuId
                        else -> tabRouter.pushView(it.controller, it.animationType, asRoot = it.asRoot)

                    }
                }.addTo(viewModel.disposeBag)
    }

    override fun onDestroy() {
        super.onDestroy()
        NavManager.clearTabState()
    }

    override fun callback(data: Any?) {
        super.callback(data)
        tabRouter.lastController?.let {
            (it as BaseView<*, *>).callback(data)
        }

    }

}

