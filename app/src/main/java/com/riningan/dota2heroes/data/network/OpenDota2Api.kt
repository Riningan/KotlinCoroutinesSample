package com.riningan.dota2heroes.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface OpenDota2Api {
    @GET("heroStats")
    fun getHeroes(): Deferred<List<HeroResponse>>
}