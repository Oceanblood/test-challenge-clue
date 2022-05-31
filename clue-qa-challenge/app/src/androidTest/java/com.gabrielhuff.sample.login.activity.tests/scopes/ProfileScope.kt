package com.gabrielhuff.sample.login.activity.tests.scopes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.gabrielhuff.sample.login.R
import com.gabrielhuff.sample.login.activity.tests.utils.waitUntilVisible
import org.hamcrest.CoreMatchers.containsString

fun profileScope(f: ProfileScope.() -> Unit) = ProfileScope().apply(f)

class ProfileScope {

    companion object {

        private val logOutButton: ViewInteraction = onView(withId(R.id.logout))
        private val usernameText: ViewInteraction = onView(withId(R.id.username))
        private val headerTextView: ViewInteraction = onView(withId(R.id.heading))
    }

    fun tapOnLogOut(): ProfileScope {
        waitUntilVisible(logOutButton)
        logOutButton.perform(click())
        return this
    }

    fun checkProfileScreenIsShown(): ProfileScope {
        waitUntilVisible(headerTextView)
        return this
    }

    fun checkProfileUserName(text: String): ProfileScope {
        waitUntilVisible(headerTextView)
        usernameText.check(matches(withText(containsString(text))))
        return this
    }
}