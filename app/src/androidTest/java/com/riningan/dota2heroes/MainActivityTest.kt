package com.riningan.dota2heroes

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.riningan.dota2heroes.data.network.OpenDota2Client
import com.riningan.dota2heroes.presentation.MainActivity
import com.riningan.dota2heroes.presentation.ui.list.ListAdapter
import okhttp3.mockwebserver.MockWebServer
import org.awaitility.Awaitility.await
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get: Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    private lateinit var mWebServer: MockWebServer


    @Before
    fun setUp() {
        mWebServer = MockWebServer()
        mWebServer.start(8080)
        mWebServer.setDispatcher(RequestDispatcher())
    }

    @After
    fun tearDown() {
        mWebServer.shutdown()
    }


    @Test
    fun test() {
        await().atMost(15, TimeUnit.SECONDS)
                .ignoreExceptions()
                .untilAsserted {
                    onView(withId(R.id.rvList)).check(matches(isDisplayed()))
                }
        await().atMost(20, TimeUnit.SECONDS)
                .ignoreExceptions()
                .untilAsserted {
                    onView(Matchers.allOf(isDisplayed(), withId(R.id.rvList)))
                            .check(recyclerViewItemCountAssertion(116))
                }
        onView(Matchers.allOf(isDisplayed(), withId(R.id.rvList)))
                .perform(actionOnItemAtPosition<ListAdapter.ViewHolder>(0, click()))
        await().atMost(15, TimeUnit.SECONDS)
                .ignoreExceptions()
                .untilAsserted {
                    onView(withId(R.id.tlbItem)).check(matches(isDisplayed()))
                }
        onView(withContentDescription(R.string.item_back)).perform(click())
        await().atMost(15, TimeUnit.SECONDS)
                .ignoreExceptions()
                .untilAsserted {
                    onView(withId(R.id.rvList)).check(matches(isDisplayed()))
                }
    }


    private fun recyclerViewItemCountAssertion(expectedCount: Int) =
            ViewAssertion { view, noViewFoundException ->
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
                val recyclerView = view as RecyclerView
                val adapter = recyclerView.adapter
                assertThat(adapter!!.itemCount, `is`(expectedCount))
            }


    companion object {
        init {
            OpenDota2Client.BASE_URL = "http://localhost:8080"
        }
    }
}