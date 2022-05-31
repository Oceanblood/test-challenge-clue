package com.gabrielhuff.sample.login.activity.tests.utils

import androidx.test.espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import io.github.kakaocup.kakao.screen.Screen.Companion.idle
import junit.framework.AssertionFailedError

    const val DEFAULT_TIMEOUT = 10_000
    const val DEFAULT_INTERVAL = 250
    const val TIMEOUT = 60

    private fun waitCondition(
        timeout: Int = DEFAULT_TIMEOUT,
        interval: Int = DEFAULT_INTERVAL,
        desc: String = "",
        conditionCheck: () -> Boolean
    ) {
        val timeoutMs = timeout * 1000
        var elapsed = 0
        while (!conditionCheck()) {
            if (elapsed >= timeoutMs) throw RuntimeException("$desc took more than $timeout seconds. Test stopped.")
            idle(interval.toLong())
            elapsed += interval
        }
    }

    /**
     * Espresso
     * Wait until View isVisible
     */
    fun waitUntilVisible(itemView: ViewInteraction) {
        waitCondition(timeout = TIMEOUT) { isViewVisible(itemView) }
    }

    private fun isViewVisible(itemView: ViewInteraction): Boolean {
        return try {
            itemView.check(matches(isDisplayed()))
            true
        } catch (e: AssertionFailedError) {
            false
        } catch (e: NoMatchingViewException) {
            false
        }
    }



