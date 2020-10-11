package com.dodemy.simpleactivity.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dodemy.simpleactivity.R
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Note that the Tests runs in alphabetical order
 * Note that ActivityScenario is used locally for each test, however it can also be
 * declared globally as ActivityScenarioRule with '@get: Rule' annotation
 */

/**
 * @author Maduabughichi Achilefu
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    //Tests if activity is in view
    @Test
    fun test_isActivityInView() {
        //ActivityScenario mimics an Activity which helps to run each @Test without which the test won't run
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main))
                .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibilityTitleNextButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        //Tests View visibility
        onView(withId(R.id.activity_main_title))
                .check(matches(isDisplayed())) //Use method type 1
        //Tests View visibility
        onView(withId(R.id.button_next_activity))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE))) //Or use method type 2
    }

    @Test
    fun test_isTitleTextDisplayed() {
        //ActivityScenario mimics an Activity which helps to run each @Test without which the test won't run
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        //Tests visibility of Text Title
        onView(withId(R.id.activity_main_title))
                .check(matches(withText(R.string.text_mainactivity)))
    }

    //Tests navigation to the next activity
    @Test
    fun test_navSecondaryActivity() {
        //Fakes or simulates activity with espresso
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button_next_activity)).perform(click())
        //Tests to see if the activity is in view/display
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }
}