package com.gabrielhuff.sample.login.activity.tests.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.gabrielhuff.sample.login.activity.SignUpActivity
import com.gabrielhuff.sample.login.activity.tests.scopes.profileScope
import com.gabrielhuff.sample.login.activity.tests.scopes.singUpScope
import com.gabrielhuff.sample.login.activity.tests.utils.Data.Constants.NEW_USER_NAME
import com.gabrielhuff.sample.login.activity.tests.utils.Data.Constants.NEW_USER_PASSWORD

@RunWith(AndroidJUnit4::class)
class SignUpTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SignUpActivity::class.java)

    @Test
    fun register_New_User() {
        singUpScope {
            enterUserName(NEW_USER_NAME)
            enterPassword(NEW_USER_PASSWORD)
            enterConfirmPassword(NEW_USER_PASSWORD)
            tapOnSignUp()
        }
        profileScope {
            checkProfileScreenIsShown()
            checkProfileUserName(NEW_USER_NAME)
        }
    }
}