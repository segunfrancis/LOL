package com.project.segunfrancis.lol

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.project.segunfrancis.lol.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
        onView(withId(R.id.shuffleButton)).perform(click())

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_any))
        onView(withId(R.id.shuffleButton)).perform(click())
    }
}
