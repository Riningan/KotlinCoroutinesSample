package com.riningan.dota2heroes.presentation.ui.list

import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.riningan.frarg.annotations.ArgumentedFragment


@ArgumentedFragment
class ListFragment : MvpFragment<ListView, ListPresenter>(), ListView {


    override fun createPresenter(): ListPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}