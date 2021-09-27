package com.example.kuniyakino;

import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;

import androidx.test.espresso.Root;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.kuniyakino.Utils.childAtPosition;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);
    //public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void logInTest()  {
        // TEST VISIBILITY
        onView(withId(R.id.welcomeTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.inputLoginEmail)).check(matches(isDisplayed()));
        onView(withId(R.id.inputLoginPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
        onView(withId(R.id.createAccountBtn)).check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.welcomeTitle)).check(matches(withText("Welcome To\nKuniyakino")));
        onView(withId(R.id.inputLoginEmail)).check(matches(withHint("Email")));
        onView(withId(R.id.inputLoginPassword)).check(matches(withHint("Password")));
        onView(withId(R.id.loginButton)).check(matches(withText("Login")));
        onView(withId(R.id.createAccountBtn)).check(matches(withText("Create an account")));

        //TEST USER INPUT
        onView(withId(R.id.inputLoginEmail)).perform(typeText("maxi@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputLoginEmail)).check(matches(withText("maxi@gmail.com")));
        onView(withId(R.id.inputLoginPassword)).perform(typeText("12345678"), pressImeActionButton());
        onView(withId(R.id.inputLoginPassword)).check(matches(withText("12345678")));

        //TEST ACTIVITY NAVIGATION
        onView(withId(R.id.loginButton)).perform(click());
        intended(hasComponent(MainPage.class.getName()));

        // VERIFY
        onView(withId(R.id.layout_main_page))
                .check(matches(isDisplayed()));

        // CHECK TOAST MSG
        onView(withText("Login Success")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        //SystemClock.sleep(2000);
    }

    @Test
    public void logInTest_invalidCondition()  {
        // TEST VISIBILITY
        onView(withId(R.id.inputLoginEmail)).check(matches(isDisplayed()));
        onView(withId(R.id.inputLoginPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.inputLoginEmail)).check(matches(withHint("Email")));
        onView(withId(R.id.inputLoginPassword)).check(matches(withHint("Password")));
        onView(withId(R.id.loginButton)).check(matches(withText("Login")));

        //TEST USER INPUT
        onView(withId(R.id.inputLoginEmail)).perform(typeText("invalid@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputLoginEmail)).check(matches(withText("invalid@gmail.com")));
        onView(withId(R.id.inputLoginPassword)).perform(typeText("12345678"), pressImeActionButton());
        onView(withId(R.id.inputLoginPassword)).check(matches(withText("12345678")));


        onView(withId(R.id.loginButton)).perform(click());
        // VERIFY
        // CHECK TOAST MSG
        onView(withText("Incorrect username or password.")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        //SystemClock.sleep(2000);
    }

    @Test
    public void createAccountTest()  {
        // TEST VISIBILITY
        onView(withId(R.id.createAccountBtn)).check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.createAccountBtn)).check(matches(withText("Create an account")));

        //TEST ACTIVITY NAVIGATION
        onView(withId(R.id.createAccountBtn)).perform(click());
        intended(hasComponent(CreateAccount.class.getName()));

        // VERIFY
        onView(withId(R.id.layout_create_account))
                .check(matches(isDisplayed()));

        //SystemClock.sleep(2000);

    }

}

