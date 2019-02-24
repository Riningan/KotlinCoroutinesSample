package com.riningan.dota2heroes

import arrow.core.Try
import com.nhaarman.mockitokotlin2.*
import com.riningan.dota2heroes.data.HeroesRepository
import com.riningan.dota2heroes.presentation.ui.list.ListPresenter
import com.riningan.dota2heroes.presentation.ui.list.ListView
import org.junit.Before
import org.junit.Test
import ru.terrakok.cicerone.Router


class ListPresenterTest {
    private lateinit var mMockRouter: Router
    private lateinit var mMockHeroesRepository: HeroesRepository
    private lateinit var mMockListView: ListView
    private lateinit var mListPresenter: ListPresenter


    @Before
    fun setup() {
        mMockRouter = mock()
        mMockHeroesRepository = mock {
            onBlocking { getAll() } doReturn Try { listOf(Hero1, Hero2) }
        }
        mMockListView = mock()
        mListPresenter = ListPresenter(mMockRouter, mMockHeroesRepository)
    }


    @Test
    fun update() {
        mListPresenter.update()

        verifyBlocking(mMockHeroesRepository) { getAll() }
        verify(mMockListView).updateList(any())
    }
}