package com.riningan.dota2heroes.app.di

import com.riningan.dota2heroes.data.network.OpenDota2Api
import com.riningan.dota2heroes.data.network.OpenDota2Client
import toothpick.config.Module


class NetworkModule : Module() {
    init {
        val client = OpenDota2Client()
        bind(OpenDota2Api::class.java).toInstance(client.getApi())
    }
}