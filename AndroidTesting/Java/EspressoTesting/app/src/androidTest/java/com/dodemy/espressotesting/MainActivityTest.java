package com.dodemy.espressotesting;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

//Note the static imports, which enhance the code clarity by reducing code length
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.matcher.ViewMatchers.withHint;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import androidx.test.espresso.ViewAssertion;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    // To launch the mentioned activity under testing
    @Rule
    public ActivityScenarioRule<MainActivity> mMainActivityActivityScenarioRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);
    //public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testHintVisibility() {
        // check hint visibility
        onView(withId(R.id.editTextName)).check(matches(withHint("Enter Name")));
        // enter name
        onView(withId(R.id.editTextName)).perform(typeText("Pavneet"), closeSoftKeyboard());
        onView(withId(R.id.editTextName)).check(matches(withText("Pavneet")));
    }

    @Test
    public void testButtonClick() {
        // enter name`
        onView(withId(R.id.editTextName)).perform(typeText("Pavneet"), closeSoftKeyboard());
        // clear text
        onView(withText("Clear")).perform(click());
        // check hint visibility after the text is cleared
        onView(withId(R.id.editTextName)).check(matches(withHint("Enter Name")));
    }
}