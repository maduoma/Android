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

@RunWith(AndroidJUnit4ClassRunner::class)
class ThirdActivityTest {
    @get : Rule
    val activityScenarioRule = ActivityScenarioRule(ThirdActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.thirdActivity))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_isActivityTitleVisible() {
        onView(withId(R.id.thirdActivityTitle))
                .check(matches(isDisplayed()))
        onView(withId(R.id.backToSecondActBtnTitle))
                .check(matches(isDisplayed()))
    }

    @Test
    fun test_isTextTitleDisplayed() {
        onView(withId(R.id.thirdActivityTitle))
                .check(matches(withText(R.string.third_activity_title)))
    }
}