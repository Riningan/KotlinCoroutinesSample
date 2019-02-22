package com.riningan.dota2heroes.app

import androidx.multidex.MultiDexApplication
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.smoothie.module.SmoothieApplicationModule


class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Toothpick.setConfiguration(Configuration.forProduction())
        val appScope = Toothpick.openScope(this.javaClass.canonicalName)
        initToothpick(appScope)
    }


    fun initToothpick(appScope: Scope) {
        appScope.installModules(SmoothieApplicationModule(this))
    }
}