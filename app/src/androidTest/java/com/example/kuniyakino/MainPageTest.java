package com.example.kuniyakino;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.kuniyakino.Utils.childAtPosition;
import static org.hamcrest.Matchers.allOf;

public class MainPageTest  {
    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);
    //public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initCondition(){
        onView(withId(R.id.inputLoginEmail)).perform(typeText("maxi@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputLoginPassword)).perform(typeText("12345678"), pressImeActionButton());

        onView(withId(R.id.loginButton)).perform(click());
        intended(hasComponent(MainPage.class.getName()));
        //SystemClock.sleep(2000);
    }

    @Test
    public void clickButtonSearchTest()  {
        //https://stackoverflow.com/questions/31301036/select-item-in-navigationview-with-espresso
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.ic_search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        // TEST VISIBILITY
        bottomNavigationItemView.check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        bottomNavigationItemView.check(matches(withContentDescription("Search")));

        // VERIFY
        onView(withId(R.id.layout_fragment_search))
                .check(matches(isDisplayed()));

        //SystemClock.sleep(2000);

        pressBack();

        onView(withId(R.id.layout_main_page))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickButtonStoreTest()  {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.ic_location), withContentDescription("Stores"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        // TEST VISIBILITY
        bottomNavigationItemView.check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        bottomNavigationItemView.check(matches(withContentDescription("Stores")));

        // VERIFY
        onView(withId(R.id.layout_fragment_store))
                .check(matches(isDisplayed()));

        //SystemClock.sleep(2000);

        pressBack();

        onView(withId(R.id.layout_main_page))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickButtonAccountTest()  {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.ic_account), withContentDescription("Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        // TEST VISIBILITY
        bottomNavigationItemView.check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        bottomNavigationItemView.check(matches(withContentDescription("Account")));

        // VERIFY
        onView(withId(R.id.layout_fragment_account))
                .check(matches(isDisplayed()));

        //SystemClock.sleep(2000);

        pressBack();

        onView(withId(R.id.layout_main_page))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickButtonHomeTest()  {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.ic_home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        // TEST VISIBILITY
        bottomNavigationItemView.check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        bottomNavigationItemView.check(matches(withContentDescription("Home")));

        // VERIFY
        onView(withId(R.id.layout_fragment_home))
                .check(matches(isDisplayed()));

        SystemClock.sleep(2000);
    }
}