package com.cigital.insecurepay.activity;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.cigital.insecurepay.R;
import com.cigital.insecurepay.common.Constants;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.not;

public class LoginTest {

    public static Activity activityObj;

    @Rule
    public final ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginPassTest() {

        activityObj = loginActivityActivityTestRule.getActivity();
        // Getting database and deleting it
        activityObj = loginActivityActivityTestRule.getActivity();
        activityObj.deleteDatabase(activityObj.getString(R.string.tableLoginTrials));
        activityObj.finish();
        activityObj.startActivity(activityObj.getIntent());

        onView(withId(R.id.username)).
                perform(replaceText(Constants.correctUsername), closeSoftKeyboard());
        onView(withId(R.id.password)).
                perform(replaceText(Constants.defaultPassword), closeSoftKeyboard());

        // First attempt with correct username and password
        onView(withId(R.id.btnSignIn))
                .perform(click());
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText(R.string.login_successful))
                .inRoot(withDecorView(not(activityObj.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}
