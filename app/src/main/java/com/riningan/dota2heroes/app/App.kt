package com.riningan.dota2heroes.app

import androidx.multidex.MultiDexApplication
import com.riningan.dota2heroes.BuildConfig
import com.riningan.util.Logger
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.smoothie.module.SmoothieApplicationModule


class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        // Logger
        Logger.Config
            .removeApplicationId(BuildConfig.APPLICATION_ID)
            .addPreffix(BuildConfig.VERSION_NAME)

        initToothpick()
    }


    private fun initToothpick() {
        Toothpick.setConfiguration(Configuration.forProduction())
        val appScope = Toothpick.openScope(this.javaClass.canonicalName)
        appScope.installModules(SmoothieApplicationModule(this))
    }


    companion object {
        private lateinit var mInstance: App


        fun getContext() = mInstance
    }
}