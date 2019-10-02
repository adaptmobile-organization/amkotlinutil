package dk.adaptmobile.amkotlinutil.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log.e
import androidx.fragment.app.FragmentActivity
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import dk.adaptmobile.amkotlinutil.extensions.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject

abstract class BaseActivity : FragmentActivity() {
    protected lateinit var mainRouter: Router
    protected lateinit var modalRouter: Router
    private val disposeBag = CompositeDisposable()
    private var previousController : Controller? = null

    protected fun handleRouting() {
        subscribeToRouting(NavManager.mainRouting, mainRouter)
        subscribeToRouting(NavManager.modalRouting, modalRouter)
    }

    private fun subscribeToRouting(routing: PublishSubject<BaseRouting>, router: Router) {
        routing
                .doOnAndroidMain()
                .subscribe(
                        {
                            when (it) {
                                is BaseRouting.Back -> {
                                    router.goBack(it.amount, it.data)

                                    handleEmptyModalRouter(routing, router, it.data)
                                }
                                is BaseRouting.CloseAll -> {
                                    if (router.isNotEmpty()) {
                                        router.goBack(router.backstackSize)
                                    }
                                    handleEmptyModalRouter(routing, router)
                                }

                                else ->  {
                                    if (modalRouter.backstackSize <= 0 || it.controller?.javaClass != modalRouter.lastController?.javaClass) {
                                        router.pushView(it.controller, it.animationType, asRoot = it.asRoot, retain = it.retain)
                                    }
                                }
                            }
                        },
                        {
                            e (javaClass.name, "Error subscribing: $it")
                        }
                )
                .addTo(disposeBag)
    }

    private fun handleEmptyModalRouter(routing: PublishSubject<BaseRouting>, router: Router, data: Any? = null) {
        //If the modal router is empty, we need to clear it in the nav manager
        if (routing == NavManager.modalRouting && router.isEmpty()) {
            NavManager.clearCurrentRouting()

            val mainController = mainRouter.lastController as BaseView<*, *>
            mainController.callback(data)
        }
    }

    override fun onBackPressed() {
        if (modalRouter.backstackSize > 1) {
            modalRouter.handleBack()
        } else if (modalRouter.backstackSize == 1) {
            NavManager.closeModal()
        } else if (!mainRouter.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        NavManager.activityDestroyed()
        if (!disposeBag.isDisposed) {
            disposeBag.dispose()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mainRouter.onActivityResult(requestCode, resultCode, data)
    }


}