package com.riningan.dota2heroes.presentation.ui.item

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.riningan.dota2heroes.data.HeroesRepository
import com.riningan.frarg.annotations.Argument
import com.riningan.frarg.annotations.ArgumentedFragment
import com.riningan.util.Logger
import kotlinx.coroutines.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@ArgumentedFragment(fragmentClass = ItemFragment::class)
class ItemPresenter @Inject constructor(private val mRouter: Router,
                                        private val mHeroesRepository: HeroesRepository) : MvpBasePresenter<ItemView>() {
    @Argument
    var mHeroId = 0

    private var myJob: Job? = null


    fun update() {
        Logger.debug()
        myJob = CoroutineScope(Dispatchers.IO).launch {
            mHeroesRepository
                    .getById(mHeroId)
                    .fold({ t ->
                        Logger.error(t)
                        withContext(Dispatchers.Main) {
                            ifViewAttached { it.showError(t.localizedMessage) }
                        }
                    }) { hero ->
                        withContext(Dispatchers.Main) {
                            ifViewAttached { it.render(hero) }
                        }
                    }

        }
    }

    fun stop() {
        Logger.debug()
        myJob?.cancel()
    }

    fun onBackClick() {
        Logger.debug()
        mRouter.exit()
    }
}