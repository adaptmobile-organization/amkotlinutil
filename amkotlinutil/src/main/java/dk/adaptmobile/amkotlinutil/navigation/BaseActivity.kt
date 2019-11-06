package dk.adaptmobile.amkotlinutil.navigation

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.github.ajalt.timberkt.e
import com.greysonparrelli.permiso.Permiso
import dk.adaptmobile.amkotlinutil.extensions.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject



abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mainRouter: Router
    private lateinit var modalRouter: Router
    private val disposeBag = CompositeDisposable()

    protected fun setup(mainContainer: ChangeHandlerFrameLayout, modalContainer: ChangeHandlerFrameLayout, savedInstanceState: Bundle?) {
        mainRouter = Conductor.attachRouter(this, mainContainer, savedInstanceState)
        modalRouter = Conductor.attachRouter(this, modalContainer, savedInstanceState)
        modalRouter.setPopsLastView(true) //We want it to be possible to pop the last view on the modal controller only

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

                                else -> {
                                    if (modalRouter.backstackSize <= 0 || it.controller?.javaClass != modalRouter.lastController?.javaClass) {
                                        router.pushView(it.controller, it.animationType, asRoot = it.asRoot, retain = it.retain)
                                    }
                                }
                            }
                        },
                        {
                            e { "Error subscribing: $it" }
                        }
                )
                .addTo(disposeBag)
    }

    private fun handleEmptyModalRouter(routing: PublishSubject<BaseRouting>, router: Router, data: Any? = null) {
        // If the modal router is empty, we need to clear it in the nav manager
        if (routing == NavManager.modalRouting && router.isEmpty()) {
            NavManager.clearCurrentRouting()

            val mainController = mainRouter.lastController as? BaseView<*, *>
            mainController?.callback(data)
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
        Permiso.getInstance().setActivity(this)
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

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Permiso.getInstance().onRequestPermissionResult(requestCode, permissions, grantResults)
    }
}
