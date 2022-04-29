package com.bisa.submissionone.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bisa.submissionone.R
import com.bisa.submissionone.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule


import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie(){
        onView(withId(R.id.rv_movies))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun testLoadTv() {
        onView(withText("tv shows"))
                .perform(click())
        onView(withId(R.id.rv_tv_show))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun testLoadDetailMovie() {
        onView(withId(R.id.rv_movies))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.bg_detail_poster))
                .check(matches(isDisplayed()))

        onView(withId(R.id.img_detail_poster))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_title))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_tagline))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_release))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_duration))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_category))
                .check(matches(isDisplayed()))

        onView(withId(R.id.rv_detail_actor))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_detail_actor))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(6))

    }

    @Test
    fun testLoadDetailTvShow() {
        onView(withText("tv shows"))
                .perform(click())
        onView(withId(R.id.rv_tv_show))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.bg_detail_poster))
                .check(matches(isDisplayed()))

        onView(withId(R.id.img_detail_poster))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_title))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_tagline))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_release))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_duration))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_category))
                .check(matches(isDisplayed()))

        onView(withId(R.id.rv_detail_actor))
                .check(matches(isDisplayed()))

    }

    @Test
    fun testLoadFavoriteMovies(){
        onView(withId(R.id.rv_movies))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_favorite))
                .perform(click())

        onView(isRoot())
                .perform(pressBack())

        onView(withId(R.id.action_favorite))
                .perform(click())
        onView(withId(R.id.rv_favorite_movies))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movies))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.bg_detail_poster))
                .check(matches(isDisplayed()))

        onView(withId(R.id.img_detail_poster))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_title))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_tagline))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_release))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_duration))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_category))
                .check(matches(isDisplayed()))

        onView(withId(R.id.rv_detail_actor))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_detail_actor))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(6))
    }

    @Test
    fun testLoadFavoriteTvShows(){
        onView(withText("tv shows"))
                .perform(click())
        onView(withId(R.id.rv_tv_show))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.img_favorite))
                .perform(click())

        onView(isRoot())
                .perform(pressBack())
        onView(withId(R.id.action_favorite))
                .perform(click())
        onView(withText("tv shows"))
                .perform(click())
        onView(withId(R.id.rv_tv_favorite_show))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_favorite_show))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.bg_detail_poster))
                .check(matches(isDisplayed()))

        onView(withId(R.id.img_detail_poster))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_title))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_tagline))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_release))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_duration))
                .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_category))
                .check(matches(isDisplayed()))

        onView(withId(R.id.rv_detail_actor))
                .check(matches(isDisplayed()))

    }
}