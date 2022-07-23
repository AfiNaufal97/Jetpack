package com.afinaufal.androiddasar.afinaufalsubmission_1.home

import android.os.TestLooperManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.afinaufal.androiddasar.afinaufalsubmission_1.R
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesMovie
import com.afinaufal.androiddasar.afinaufalsubmission_1.utility.ValuesTv
import org.junit.Rule
import org.junit.Test
import org.junit.runner.OrderWith

class MyMovieActivityTest{
    private val dummyMovie = ValuesMovie.movieData()
    private val dummyTv = ValuesTv.tvData()

    @get:Rule
    var activityRule = ActivityScenarioRule(MyMovieActivity::class.java)

    @Test
    fun loadViewMovie(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadViewMovieDetail(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_title_movie))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].nameMovie)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_raiting_value_movie))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].raiting)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_genre_movie_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].genre)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_year_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].year)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_duration_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].duration)))
        Espresso.onView(ViewMatchers.withId(R.id.keteranganMovie))
                .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].sinopsis)))
    }


    @Test
    fun loadViewTv(){
        Espresso.onView(ViewMatchers.withText("TV")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadViewTvDetail(){
        Espresso.onView(ViewMatchers.withText("TV")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_title_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].nameShow)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_raiting_value_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].raiting)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_genre_tv_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].genre)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_duration_tv_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].duration)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_sinopsis_value_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].sinopsis)))
    }

    @Test
    fun clickShareMovie(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.btn_share)).perform(ViewActions.click())
    }

    @Test
    fun clickShareTv(){
        Espresso.onView(ViewMatchers.withText("TV")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        ViewActions.click()
                ))
        Espresso.onView(ViewMatchers.withId(R.id.btn_share_tv)).perform(ViewActions.click())
    }

    @Test
    fun clickTrailerTv(){
        Espresso.onView(ViewMatchers.withText("TV")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        ViewActions.click()
                ))
        Espresso.onView(ViewMatchers.withId(R.id.btn_trailer_tv)).perform(ViewActions.click())
    }

    @Test
    fun clickFavoritTv(){
        Espresso.onView(ViewMatchers.withText("TV")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.iv_foavorit_tv)).perform(ViewActions.click())
    }


    @Test
    fun clickTrailerMovie(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                        ViewActions.click()
                ))
        Espresso.onView(ViewMatchers.withId(R.id.btn_trailer)).perform(ViewActions.click())
    }


    @Test
    fun loadMyProfile() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_profile)).perform(ViewActions.click())
    }

    @Test
    fun MyFavoritMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_to_favorit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorit_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorit_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }


    @Test
    fun clickMyFavoritMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_to_favorit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorit_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorit_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_title_movie))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].nameMovie)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_raiting_value_movie))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].raiting)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_genre_movie_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].genre)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_year_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].year)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_duration_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].duration)))
        Espresso.onView(ViewMatchers.withId(R.id.keteranganMovie))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].sinopsis)))
    }

    @Test
    fun MyFavoritTv(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_to_favorit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorit_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorit_tv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun myFavoritTvDetail(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_to_favorit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorit_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.tv_detail_title_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].nameShow)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_raiting_value_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].raiting)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_genre_tv_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].genre)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_duration_tv_value))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].duration)))
        Espresso.onView(ViewMatchers.withId(R.id.tv_sinopsis_value_tv))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTv[0].sinopsis)))
    }

    @Test
    fun clickFavoritMyMovie(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(ViewMatchers.withId(R.id.iv_favorite)).perform(ViewActions.click())
    }
}