package com.riningan.dota2heroes.presentation.route

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.riningan.dota2heroes.presentation.ui.item.ItemFragment
import com.riningan.dota2heroes.presentation.ui.list.ListFragment
import com.riningan.frarg.processor.FragmentBuilder
import ru.terrakok.cicerone.android.SupportAppNavigator


class Navigator constructor(activity: FragmentActivity, containerId: Int) : SupportAppNavigator(activity, containerId) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

    override fun createFragment(screenKey: String?, data: Any?): Fragment? =
        when (screenKey) {
            ListFragment::class.java.canonicalName -> FragmentBuilder.newListFragmentInstance()
            ItemFragment::class.java.canonicalName -> FragmentBuilder.newItemFragmentInstance(data as Int)
            else -> null
        }
}