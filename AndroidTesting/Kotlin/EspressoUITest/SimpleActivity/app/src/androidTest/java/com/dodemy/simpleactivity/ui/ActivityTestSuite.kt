package com.dodemy.simpleactivity.ui

import org.junit.runner.RunWith
import org.junit.runners.Suite


/**
 * This a way of combining or aggregating all activities/fragments tests and run them
 * at the same time. TestSuite: Combining all tests and make them run at once.
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
        MainActivityTest::class,
        SecondaryActivityTest::class,
        ThirdActivityTest::class
)
class ActivityTestSuite