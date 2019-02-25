package com.riningan.dota2heroes.presentation.ui.list

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.riningan.dota2heroes.data.HeroesRepository
import com.riningan.dota2heroes.data.model.Hero
import com.riningan.dota2heroes.presentation.ui.item.ItemFragment
import com.riningan.util.Logger
import kotlinx.coroutines.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class ListPresenter @Inject constructor(private val mRouter: Router,
                                        private val mHeroesRepository: HeroesRepository) : MvpBasePresenter<ListView>() {
    private var myJob: Job? = null


    fun update() {
        Logger.debug()
        myJob = CoroutineScope(Dispatchers.IO).launch {
            mHeroesRepository
                    .getAll()
                    .fold({ t ->
                        Logger.error(t)
                        withContext(Dispatchers.Main) {
                            ifViewAttached { it.showError(t.localizedMessage) }
                        }
                    }) { heroes ->
                        withContext(Dispatchers.Main) {
                            ifViewAttached { it.updateList(heroes) }
                        }
                    }
        }
    }

    fun stop() {
        Logger.debug()
        myJob?.cancel()
    }

    fun onItemClick(hero: Hero) {
        Logger.debug("id", hero.id)
        mRouter.navigateTo(ItemFragment::class.java.canonicalName, hero.id)
    }
}