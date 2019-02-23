package com.riningan.dota2heroes.presentation.ui.item

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.riningan.dota2heroes.R
import com.riningan.dota2heroes.data.model.Hero
import com.riningan.dota2heroes.presentation.MainActivity
import com.riningan.frarg.FrargBinder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item.*
import toothpick.Toothpick
import javax.inject.Inject


class ItemFragment : MvpFragment<ItemView, ItemPresenter>(), ItemView {
    @Inject
    lateinit var mPresenter: ItemPresenter


    override fun createPresenter() = mPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Toothpick.inject(this, Toothpick.openScope(MainActivity::class.java.canonicalName))
        FrargBinder.bind(mPresenter, arguments!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_item, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tlbItem.setNavigationOnClickListener { presenter.onBackClick() }
        presenter.update()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    override fun render(hero: Hero) {
        Picasso.get()
                .load("https://api.opendota.com" + hero.img)
                .into(ivItem)
    }

    override fun showError(message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT).show()
    }
}