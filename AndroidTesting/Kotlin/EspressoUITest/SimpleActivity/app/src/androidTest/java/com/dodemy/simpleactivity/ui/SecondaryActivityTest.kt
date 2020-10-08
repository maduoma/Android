package com.dodemy.simpleactivity.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dodemy.simpleactivity.R
import org.junit.Rule
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
class SecondaryActivityTest {
    @get : Rule
    val activityScenarioRule = ActivityScenarioRule(SecondaryActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.activity_secondary_title))
                .check(matches(isDisplayed()))
    }

    @Test
    fun text_VisibilityTitleBackButton() {
        onView(withId(R.id.activity_secondary_title))
                .check(matches(isDisplayed()))

        onView(withId(R.id.button_back))
                .check(matches(isDisplayed()))
    }

    @Test
    fun text_isTextTitleDisplayed() {
        onView(withId(R.id.activity_secondary_title))
                .check(matches(withText(R.string.text_secondaryactivity)))
    }
}