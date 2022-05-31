package com.gabrielhuff.sample.login.activity.tests.scopes

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.gabrielhuff.sample.login.R
import com.gabrielhuff.sample.login.activity.tests.utils.waitUntilVisible

fun loginScope(f: LoginScope.() -> Unit) = LoginScope().apply(f)

class LoginScope {

    companion object {

        private val usernameField: ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.username))
        private val passwordField: ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.password))
        private val loginButton: ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.login))
        private val signUpButton: ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.signup))
    }

    fun enterUserName(text: String): LoginScope {
        waitUntilVisible(usernameField)
        usernameField.perform(click())
        usernameField.perform(ViewActions.typeTextIntoFocusedView(text)).check(
            ViewAssertions.matches(
                withText(text)
            )
        )
        closeSoftKeyboard()
    return this
    }

    fun enterPassword(text: String): LoginScope {
        passwordField.perform(click())
        passwordField.perform(ViewActions.typeTextIntoFocusedView(text)).check(
            ViewAssertions.matches(
                withText(text)
            )
        )
        closeSoftKeyboard()
        return this
    }

    fun tapOnLogin(): LoginScope {
        loginButton.perform(click())
        return this
    }

    fun tapOnSignUp(): LoginScope {
        waitUntilVisible(signUpButton)
        signUpButton.perform(click())
        return this
    }
}