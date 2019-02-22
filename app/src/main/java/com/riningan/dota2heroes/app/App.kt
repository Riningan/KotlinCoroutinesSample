package com.riningan.dota2heroes.app

import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import com.riningan.dota2heroes.BuildConfig
import com.riningan.util.Logger
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
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
        // LeakCanary
        if (BuildConfig.DEBUG) {
            enabledStrictMode()
            if (!LeakCanary.isInAnalyzerProcess(this)) {
                mRefWatcher = LeakCanary.install(this)
            }
        }

        initToothpick()
    }


    private fun initToothpick() {
        Toothpick.setConfiguration(Configuration.forProduction())
        val appScope = Toothpick.openScope(this.javaClass.canonicalName)
        appScope.installModules(SmoothieApplicationModule(this))
    }


    companion object {
        private lateinit var mInstance: App
        private lateinit var mRefWatcher: RefWatcher


        fun getContext() = mInstance

        fun getRefWatcher() = mRefWatcher


        private fun enabledStrictMode() {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }
}