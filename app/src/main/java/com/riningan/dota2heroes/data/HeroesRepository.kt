package com.riningan.dota2heroes.data

import com.riningan.dota2heroes.data.model.Hero
import com.riningan.dota2heroes.data.network.OpenDota2Api


class HeroesRepository(private val mOpenDota2Api: OpenDota2Api) {
    private val mCache = arrayListOf<Hero>()


    suspend fun get(): List<Hero> {
        return if (mCache.isEmpty()) {
            val result = mOpenDota2Api.getHeroes().await()
            arrayListOf()
        } else {
            mCache
        }
    }
}