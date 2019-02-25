package com.riningan.dota2heroes.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class OpenDota2Client {
    private val mApi: OpenDota2Api


    init {
        val okHttpClient = OkHttpClient().newBuilder()
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .build()

        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        mApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(OpenDota2Api::class.java)
    }


    fun getApi() = mApi


    companion object {
        private const val TIMEOUT = 30000L
        var BASE_URL = "https://api.opendota.com/api/"
    }
}