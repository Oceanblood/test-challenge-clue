package com.gabrielhuff.sample.login.activity.tests.scopes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import com.gabrielhuff.sample.login.R
import com.gabrielhuff.sample.login.activity.tests.utils.waitUntilVisible

fun dialogScope(f: DialogScope.() -> Unit) = DialogScope().apply(f)

class DialogScope {

    companion object {

        private val closeButton: ViewInteraction = onView(withId(R.id.close))
    }

    fun verifyErrorViewIsVisible(): DialogScope {
        waitUntilVisible(closeButton)
        return this
    }
}