package com.riningan.dota2heroes

import arrow.core.Try
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyBlocking
import com.riningan.dota2heroes.data.HeroesRepository
import com.riningan.dota2heroes.presentation.ui.item.ItemPresenter
import com.riningan.dota2heroes.presentation.ui.item.ItemView
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
class ItemPresenterTest {
    private lateinit var mMockRouter: Router
    private lateinit var mMockHeroesRepository: HeroesRepository
    private lateinit var mMockItemView: ItemView
    private lateinit var mItemPresenter: ItemPresenter
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        Logger.Config.setEnabled(false)
        mMockRouter = mock()
        mMockHeroesRepository = mock {
            onBlocking { getById(Hero1.id) } doReturn Try { Hero1 }
        }
        mMockItemView = mock()
        mItemPresenter = ItemPresenter(mMockRouter, mMockHeroesRepository)
        mItemPresenter.mHeroId = Hero1.id
        mItemPresenter.attachView(mMockItemView)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }


    @Test
    fun update() {
        mItemPresenter.update()

        verifyBlocking(mMockHeroesRepository) { getById(Hero1.id) }
        verify(mMockItemView).render(Hero1)
    }

    @Test
    fun onBackClick() {
        mItemPresenter.onBackClick()

        verify(mMockRouter).exit()
    }
}