package dk.adaptmobile.amkotlinutil.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
object NavManager {

    private lateinit var tabs: List<BaseTab>
    private lateinit var baseBottomRouting: BaseRouting

    private var bottomNavShown: Boolean = false // Keeps track of whether the bottom navigation view is shown or not.
    private var currentTab: BaseTab? = null
    private var previousTab: BaseTab? = null // Holding a reference to the previous tab is necessary in order to save the state after navigating away from it

    internal val mainRouting = PublishSubject.create<BaseRouting>()
    internal val modalRouting = PublishSubject.create<BaseRouting>()
    internal val tabRouting = PublishSubject.create<BaseRouting>()
    private var previousRoutingSubject: PublishSubject<BaseRouting>? = null
    private var currentRoutingSubject: PublishSubject<BaseRouting>? = null

    init {
        // We subscribe to each routing subject individually in order to save a reference to the
        // last subject that emitted items (which will always be the "visible" router)
        // TODO: There must be a better way to do this instead of subscribing individually.
        mainRouting.subscribe {
            if (it !is BaseRouting.Back) {
                previousRoutingSubject = currentRoutingSubject
                currentRoutingSubject = mainRouting
            }

            bottomNavShown = (it is BaseRouting.BaseBottomNav)
        }

        modalRouting.subscribe {
            if (it !is BaseRouting.Back) {
                if (currentRoutingSubject != modalRouting) {
                    previousRoutingSubject = currentRoutingSubject
                }
                currentRoutingSubject = modalRouting
            }
        }

        tabRouting.subscribe {
            if (it !is BaseRouting.Back) {
                previousRoutingSubject = currentRoutingSubject
                currentRoutingSubject = tabRouting
            }
        }
    }

    /**
     * Sets up the necessary information for handling tabs.
     * Required to be called before calling openTab
     * @param baseBottomRouting
     * @param tabs
     */
    fun initTabs(baseBottomRouting: BaseRouting, tabs: List<BaseTab>) {
        this.baseBottomRouting = baseBottomRouting
        this.tabs = tabs
    }

    /**
     * Opens a view modally (on top of everything else)
     * @param routing
     */
    fun openModally(routing: BaseRouting) {
        modalRouting.onNext(routing)
    }

    fun closeModal() {
        modalRouting.onNext(BaseRouting.Back())
    }

    /**
     * Opens a view in your current router
     * @param routing
     */
    fun open(routing: BaseRouting) {
        currentRoutingSubject?.onNext(routing) ?: mainRouting.onNext(routing)
    }

    /**
     * Opens a specific tab.
     * @param tab The tab to open
     */
    fun openTab(tab: BaseTab) {
        closeAllModalViews()
        if (!bottomNavShown) { // TODO: Could maybe be if (currentRouting == tabRouting)?
            mainRouting.onNext(baseBottomRouting)
        }
        tabRouting.onNext(BaseRouting.MarkTabAsSelected(tab.id))
    }

    fun goBack(amount: Int = 1, data: Any? = null) {
        currentRoutingSubject?.onNext(BaseRouting.Back(amount, data))
    }

    private fun closeAllModalViews() {
        modalRouting.onNext(BaseRouting.CloseAll)
    }

    /**
     * Handles the selection of a tab. Handled automatically by BaseBottomNavigationView
     */
    internal fun tabSelected(item: MenuItem) {
        val tab = tabs.first { it.id == item.itemId }
        if (currentTab == tab) {
            tabRouting.onNext(BaseRouting.GoToRoot())
            return
        }

        previousTab = currentTab
        currentTab = tab

        val state = tab.state
        when (state == null) {
            true -> tabRouting.onNext(BaseRouting.SetTabRoot(tab.initalView()))
            false -> tabRouting.onNext(BaseRouting.OpenTab(state))
        }
    }

    /**
     * Saves the state to the previous tab. Handles automatically by BaseBottomNavigationView
     */
    internal fun setCurrentTabState(state: Bundle) {
        previousTab?.state = state
    }

    /**
     * Clears the tab state. Handled automatically by BaseBottomNavigationView
     */
    internal fun clearTabState() {
        tabs.forEach { it.state = null }
        currentTab = null
        previousTab = null
        clearCurrentRouting() // When the tabview detaches, we want the current routing subject to change back to the main routing
    }

    /**
     * Sets the current routing to be the previous routing. Used when closing modal views. Handled automatically
     */
    internal fun clearCurrentRouting() {
        currentRoutingSubject = previousRoutingSubject
        previousRoutingSubject = null
    }

    internal fun activityDestroyed() {
        currentRoutingSubject = mainRouting
        previousRoutingSubject = null
    }
}
