package com.gabrielhuff.sample.login.activity.tests.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.gabrielhuff.sample.login.activity.LoginActivity
import com.gabrielhuff.sample.login.activity.tests.scopes.*
import com.gabrielhuff.sample.login.activity.tests.utils.Data.Constants.NONEXISTENT_USER_NAME
import com.gabrielhuff.sample.login.activity.tests.utils.Data.Constants.NONEXISTENT_USER_PASSWORD
import com.gabrielhuff.sample.login.activity.tests.utils.Data.Constants.USER_NAME
import com.gabrielhuff.sample.login.activity.tests.utils.Data.Constants.USER_PASSWORD

@RunWith(AndroidJUnit4::class)
class LoginTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)


    @Test
    fun logIn_in_With_InValid_Credentials() {
        loginScope {
            enterUserName(NONEXISTENT_USER_NAME)
            enterPassword(NONEXISTENT_USER_PASSWORD)
            tapOnLogin()
        }
        dialogScope {
            verifyErrorViewIsVisible()
        }
    }

    @Test
    fun signUp_New_User_And_Login() {
        loginScope {
            tapOnSignUp()
        }
        singUpScope {
            enterUserName(USER_NAME)
            enterPassword(USER_PASSWORD)
            enterConfirmPassword(USER_PASSWORD)
            tapOnSignUp()
        }
        profileScope {
            tapOnLogOut()
        }
        loginScope {
            enterUserName(USER_NAME)
            enterPassword(USER_PASSWORD)
            tapOnLogin()

        }
        profileScope {
            checkProfileUserName(USER_NAME)
        }
    }
}

