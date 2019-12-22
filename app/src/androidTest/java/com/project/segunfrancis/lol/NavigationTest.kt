package com.project.segunfrancis.lol

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*
import androidx.test.espresso.action.ViewActions.*
import org.junit.Rule
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.DrawerActions

/**
 * Created by SegunFrancis
 */

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @Rule // Makes it visible for JUnit
    @JvmField
    val mainActivity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun openAnyFragmentAfterNavigationDrawerItemSelected() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_dark))
        onView(withId(R.id.imageButton)).perform(click())

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_any))
        onView(withId(R.id.imageButton)).perform(click())
    }
}