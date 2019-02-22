package com.riningan.dota2heroes.presentation.route

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import ru.terrakok.cicerone.android.SupportAppNavigator

class Navigator constructor(activity: FragmentActivity, containerId: Int) : SupportAppNavigator(activity, containerId) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

    override fun createFragment(screenKey: String?, data: Any?): android.support.v4.app.Fragment =
        when (screenKey) {
            ListFragment::class.java.canonicalName ->
        }
}