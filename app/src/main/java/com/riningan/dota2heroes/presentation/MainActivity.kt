package com.riningan.dota2heroes.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.riningan.dota2heroes.R
import com.riningan.dota2heroes.app.di.NetworkModule
import com.riningan.dota2heroes.app.di.RepositoryModule
import com.riningan.dota2heroes.app.di.RouteModule
import com.riningan.dota2heroes.presentation.route.Navigator
import com.riningan.dota2heroes.presentation.ui.list.ListFragment
import com.riningan.util.Logger
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import toothpick.smoothie.module.SmoothieAndroidXActivityModule
import javax.inject.Inject


class MainActivity : FragmentActivity() {
    @Inject
    lateinit var mRouter: Router
    @Inject
    lateinit var mNavigatorHolder: NavigatorHolder

    private var mNavigator: Navigator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.debug()
        super.onCreate(savedInstanceState)
        Toothpick.openScope(this.javaClass.canonicalName).apply {
            installModules(SmoothieAndroidXActivityModule(this@MainActivity))
            installModules(RouteModule())
            installModules(NetworkModule())
            installModules(RepositoryModule())
            Toothpick.inject(this@MainActivity, this)
        }
        setContentView(R.layout.activity_main)
        mNavigator = Navigator(this, android.R.id.content)
        mRouter.newRootScreen(ListFragment::class.java.canonicalName)
    }

    override fun onResume() {
        Logger.debug()
        super.onResume()
        mNavigatorHolder.setNavigator(mNavigator)
    }

    override fun onPause() {
        Logger.debug()
        mNavigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        Logger.debug()
        Toothpick.closeScope(this.javaClass.canonicalName)
        mNavigator = null
        super.onDestroy()
    }
}
