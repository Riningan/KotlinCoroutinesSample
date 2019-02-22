package com.riningan.dota2heroes.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface OpenDota2Api {
    @GET("/heroes")
    fun getHeroes(): Deferred<List<HeroResponse>>

    @GET("/heroes/{hero_id}/matches")
    fun getHeroMathes(@Path("hero_id") heroId: String): Deferred<List<HeroMathesResponse>>
}