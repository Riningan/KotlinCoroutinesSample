package com.riningan.dota2heroes

import arrow.core.getOrDefault
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.riningan.dota2heroes.data.HeroesRepository
import com.riningan.dota2heroes.data.network.OpenDota2Api
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.internal.verification.Times


class HeroesRepositoryUnitTest {
    private lateinit var mMockOpenDota2Api: OpenDota2Api
    private lateinit var mHeroesRepository: HeroesRepository


    @Before
    fun setup() {
        mMockOpenDota2Api = mock {
            on { getHeroes() } doReturn CompletableDeferred(listOf(HeroResponse1, HeroResponse2))
        }
        mHeroesRepository = HeroesRepository(mMockOpenDota2Api)
    }


    @Suppress("DeferredResultUnused")
    @Test
    fun getAll() {
        runBlocking {
            val heroes = mHeroesRepository
                    .getAll()
                    .getOrDefault { arrayListOf() }
            verify(mMockOpenDota2Api).getHeroes()
            assertEquals(heroes.size, 2)
            assertEquals(heroes[0], Hero1)
            assertEquals(heroes[1], Hero2)

            // call again
            mHeroesRepository
                    .getAll()
                    .getOrDefault { arrayListOf() }
            verify(mMockOpenDota2Api, Times(1)).getHeroes()
        }
    }

    @Suppress("DeferredResultUnused")
    @Test
    fun getById() {
        runBlocking {
            val hero = mHeroesRepository
                    .getById(Hero1.id)
                    .getOrDefault { Hero1 }
            verify(mMockOpenDota2Api).getHeroes()
            assertEquals(hero, Hero1)

            // call again
            mHeroesRepository
                    .getAll()
                    .getOrDefault { arrayListOf() }
            verify(mMockOpenDota2Api, Times(1)).getHeroes()
        }
    }
}
