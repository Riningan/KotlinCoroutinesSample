package com.riningan.dota2heroes.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface OpenDota2Api {
    @GET("heroStats")
    fun getHeroes(): Deferred<List<HeroResponse>>
}