package com.riningan.dota2heroes.presentation.ui.list

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import kotlinx.coroutines.*


class ListPresenter : MvpBasePresenter<ListView>() {
    private var myJob: Job? = null


    fun update() {
        myJob = CoroutineScope(Dispatchers.IO).launch {
//            val result = repo.getLeagues()
            withContext(Dispatchers.Main) {
                ifViewAttached {  }
            }
        }
    }

    fun stop() {
        myJob?.cancel()
    }

    fun onItemClick() {

    }
}