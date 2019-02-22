package com.riningan.dota2heroes.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.riningan.dota2heroes.R
import com.riningan.frarg.annotations.ArgumentedFragment
import kotlinx.android.synthetic.main.fragment_list.*


@ArgumentedFragment
class ListFragment : MvpFragment<ListView, ListPresenter>(), ListView, ListAdapter.OnItemClickListener {
    override fun createPresenter(): ListPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override fun onItemClick() {
        presenter.onItemClick()
    }
}