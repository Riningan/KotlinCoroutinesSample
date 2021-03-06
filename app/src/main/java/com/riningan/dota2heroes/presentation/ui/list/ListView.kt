package com.riningan.dota2heroes.presentation.ui.list

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.riningan.dota2heroes.data.model.Hero


interface ListView : MvpView {
    fun updateList(heroes: List<Hero>)
    fun showError(message: String)
}