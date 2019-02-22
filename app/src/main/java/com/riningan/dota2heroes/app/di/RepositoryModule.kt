package com.riningan.dota2heroes.app.di

import com.riningan.dota2heroes.data.HeroesRepository
import toothpick.config.Module


class RepositoryModule : Module() {
    init {
        bind(HeroesRepository::class.java).singletonInScope()
    }
}