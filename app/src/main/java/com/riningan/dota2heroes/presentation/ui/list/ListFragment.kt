package com.riningan.dota2heroes.presentation.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.riningan.dota2heroes.R
import com.riningan.dota2heroes.data.model.Hero
import com.riningan.dota2heroes.presentation.MainActivity
import com.riningan.frarg.annotations.ArgumentedFragment
import kotlinx.android.synthetic.main.fragment_list.*
import toothpick.Toothpick
import javax.inject.Inject


@ArgumentedFragment
class ListFragment : MvpFragment<ListView, ListPresenter>(), ListView, ListAdapter.OnItemClickListener {
    @Inject
    lateinit var mPresenter: ListPresenter


    override fun createPresenter() = mPresenter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Toothpick.inject(this, Toothpick.openScope(MainActivity::class.java.canonicalName))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListAdapter(this@ListFragment)
        }
        presenter.update()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    override fun onItemClick(hero: Hero) {
        presenter.onItemClick(hero)
    }

    override fun updateList(heroes: List<Hero>) {
        (rvList.adapter as ListAdapter).apply {
            setHeroes(heroes)
            notifyDataSetChanged()
        }
    }

    override fun showError(message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_SHORT).show()
    }
}