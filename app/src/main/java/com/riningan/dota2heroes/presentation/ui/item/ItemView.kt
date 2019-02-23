package com.riningan.dota2heroes.presentation.ui.item

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.riningan.dota2heroes.data.model.Hero


interface ItemView : MvpView {
    fun render(hero: Hero)
    fun showError(message: String)
}