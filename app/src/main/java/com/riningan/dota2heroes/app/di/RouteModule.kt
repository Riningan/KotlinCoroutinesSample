package com.riningan.dota2heroes.app.di

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module


class RouteModule : Module() {
    init {
        val router = Router()
        val cicerone = Cicerone.create<Router>(router)
        bind(Router::class.java).toInstance(router)
        bind(Cicerone::class.java).toInstance(cicerone)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}