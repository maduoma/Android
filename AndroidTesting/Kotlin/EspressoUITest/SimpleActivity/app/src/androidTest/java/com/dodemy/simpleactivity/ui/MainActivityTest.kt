package com.dodemy.simpleactivity.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dodemy.simpleactivity.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Test
    //Tests if activity is in view
    fun test_isActivityInView() {
        //Use ActivityScenario that helps to run each @Test
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main))
                .check(matches(isDisplayed()))

        //Tests View visibility
        onView(withId(R.id.activity_main_title))
                .check(matches(isDisplayed())) //Use method type 1
        //Tests View visibility
        onView(withId(R.id.button_next_activity))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE))) //Or use method type 2

    }

    @Test
    fun test_isTitleTextDisplayed() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        //Tests visibility of Activity Title
        onView(withId(R.id.activity_main_title))
                .check(matches(withText(R.string.text_mainactivity)))
    }
}