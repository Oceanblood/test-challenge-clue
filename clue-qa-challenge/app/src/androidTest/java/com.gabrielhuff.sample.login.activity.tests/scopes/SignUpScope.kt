package com.gabrielhuff.sample.login.activity.tests.scopes

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.gabrielhuff.sample.login.R
import com.gabrielhuff.sample.login.activity.tests.utils.waitUntilVisible

fun singUpScope(f: SignUpScope.() -> Unit) = SignUpScope().apply(f)

class SignUpScope {

    companion object {

        private val usernameField: ViewInteraction = onView(withId(R.id.username))
        private val passwordField: ViewInteraction = onView(withId(R.id.password))
        private val confirmPasswordField: ViewInteraction = onView(withId(R.id.password2))
        private val signUpButton: ViewInteraction = onView(withId(R.id.signup))
    }

    fun enterUserName(text: String): SignUpScope {
        waitUntilVisible(usernameField)
        usernameField.perform(click())
        usernameField.perform(typeTextIntoFocusedView(text)).check(
            ViewAssertions.matches(
                withText(text)
            )
        )
        closeSoftKeyboard()
        return this
    }

    fun enterPassword(text: String): SignUpScope {
        passwordField.perform(click())
        passwordField.perform(typeTextIntoFocusedView(text)).check(
            ViewAssertions.matches(
                withText(text)
            )
        )
        closeSoftKeyboard()
        return this
    }

    fun enterConfirmPassword(text: String): SignUpScope {
        confirmPasswordField.perform(click())
        confirmPasswordField.perform(typeTextIntoFocusedView(text)).check(
            ViewAssertions.matches(
                withText(text)
            )
        )
        closeSoftKeyboard()
        return this
    }

    fun tapOnSignUp(): SignUpScope {
        waitUntilVisible(signUpButton)
        signUpButton.perform(click())
        return this
    }
}