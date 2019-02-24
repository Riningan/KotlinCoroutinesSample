package com.riningan.dota2heroes.data

import arrow.core.Try
import com.riningan.dota2heroes.data.model.Hero
import com.riningan.dota2heroes.data.network.OpenDota2Api
import javax.inject.Inject


open class HeroesRepository @Inject constructor(private val mOpenDota2Api: OpenDota2Api) {
    private val mCache = arrayListOf<Hero>()


    suspend fun getAll(): Try<List<Hero>> = Try { get() }

    suspend fun getById(heroId: Int): Try<Hero> = Try {
        get().find { it.id == heroId } ?: throw NullPointerException()
    }

    private suspend fun get(): List<Hero> {
        if (mCache.isEmpty()) {
            mCache.addAll(mOpenDota2Api
                    .getHeroes()
                    .await()
                    .map {
                        Hero(it.id, it.name, it.localizedName, it.img)
                    })
        }
        return mCache
    }
}