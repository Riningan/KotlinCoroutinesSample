package com.riningan.dota2heroes

import arrow.core.Try
import com.nhaarman.mockitokotlin2.*
import com.riningan.dota2heroes.data.HeroesRepository
import com.riningan.dota2heroes.presentation.ui.item.ItemFragment
import com.riningan.dota2heroes.presentation.ui.list.ListPresenter
import com.riningan.dota2heroes.presentation.ui.list.ListView
import com.riningan.util.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.terrakok.cicerone.Router


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class ListPresenterTest {
    private lateinit var mMockRouter: Router
    private lateinit var mMockHeroesRepository: HeroesRepository
    private lateinit var mMockListView: ListView
    private lateinit var mListPresenter: ListPresenter
    private val mMainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        Dispatchers.setMain(mMainThreadSurrogate)
        Logger.Config.setEnabled(false)
        mMockRouter = mock()
        mMockHeroesRepository = mock {
            onBlocking { getAll() } doReturn Try { listOf(Hero1, Hero2) }
        }
        mMockListView = mock()
        mListPresenter = ListPresenter(mMockRouter, mMockHeroesRepository)
        mListPresenter.attachView(mMockListView)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mMainThreadSurrogate.close()
    }


    @Test
    fun update() {
        mListPresenter.update()

        verifyBlocking(mMockHeroesRepository) { getAll() }
        verify(mMockListView).updateList(any())
    }

    @Test
    fun onItemClick() {
        mListPresenter.onItemClick(Hero1)

        verify(mMockRouter).navigateTo(ItemFragment::class.java.canonicalName, Hero1.id)
    }
}