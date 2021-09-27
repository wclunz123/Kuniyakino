package com.example.kuniyakino.fragments;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.kuniyakino.MainActivity;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;

import junit.framework.TestCase;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.kuniyakino.Utils.childAtPosition;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class StoreFragmentTest {
    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void initCondition(){
        onView(withId(R.id.inputLoginEmail)).perform(typeText("maxi@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputLoginPassword)).perform(typeText("12345678"), pressImeActionButton());

        onView(withId(R.id.loginButton)).perform(click());
        intended(hasComponent(MainPage.class.getName()));
        SystemClock.sleep(2000);

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.ic_location), withContentDescription("Stores"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
    }

    @Test
    public void testStore(){

        // TEST VISIBILITY
        onView(withId(R.id.mallName1)).check(matches(isDisplayed()));
        onView(withId(R.id.mallAddress1)).check(matches(isDisplayed()));
        onView(withId(R.id.mallNumber1)).check(matches(isDisplayed()));
        onView(withId(R.id.mallLocation1)).check(matches(isDisplayed()));
        onView(withId(R.id.mallName2)).check(matches(isDisplayed()));
        onView(withId(R.id.mallAddress2)).check(matches(isDisplayed()));
        onView(withId(R.id.mallNumber2)).check(matches(isDisplayed()));
        onView(withId(R.id.mallLocation2)).check(matches(isDisplayed()));
        onView(withId(R.id.mallName3)).check(matches(isDisplayed()));
        onView(withId(R.id.mallAddress3)).check(matches(isDisplayed()));
        onView(withId(R.id.mallNumber3)).check(matches(isDisplayed()));
        onView(withId(R.id.mallLocation3)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.mallName1)).check(matches(withText("VivoCity Mall")));
        onView(withId(R.id.mallAddress1)).check(matches(withText("123C VivoCity Mall #08-888\nSingapore 098585")));
        onView(withId(R.id.mallNumber1)).check(matches(withText("+65 1234 5678")));
        onView(withId(R.id.mallLocation1)).check(matches(withText("Locate Us")));
        onView(withId(R.id.mallName2)).check(matches(withText("Raffles City")));
        onView(withId(R.id.mallAddress2)).check(matches(withText("68 Raffles City #02-246\nSingapore 179101")));
        onView(withId(R.id.mallNumber2)).check(matches(withText("+65 5513 1893")));
        onView(withId(R.id.mallLocation2)).check(matches(withText("Locate Us")));
        onView(withId(R.id.mallName3)).check(matches(withText("Compassone Mall")));
        onView(withId(R.id.mallAddress3)).check(matches(withText("2 Sengkang Square #B1-02\nSingapore 545078")));
        onView(withId(R.id.mallNumber3)).check(matches(withText("+65 5189 6598")));
        onView(withId(R.id.mallLocation3)).perform(ViewActions.scrollTo()).check(matches(withText("Locate Us")));

        //https://stackoverflow.com/questions/21855540/android-espresso-web-browser
        //TEST ACTIVITY NAVIGATION
        //Google Map Location 1
        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData("geo:1.2646177235390001,103.82223928388068?q=VivoCity"));
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));
        onView(withId(R.id.mallLocation1)).perform(click());
        intended(expectedIntent);
        //Google Map Location 2
        Matcher<Intent> expectedIntent2 = allOf(hasAction(Intent.ACTION_VIEW), hasData("geo:1.2942566993396947,103.85315802618969?q=RafflesCity"));
        intending(expectedIntent2).respondWith(new Instrumentation.ActivityResult(0, null));
        onView(withId(R.id.mallLocation2)).perform(click());
        intended(expectedIntent2);
        //Google Map Location 3
        Matcher<Intent> expectedIntent3 = allOf(hasAction(Intent.ACTION_VIEW), hasData("geo:1.3925426834215249,103.8950285423295?q=Compassone"));
        intending(expectedIntent3).respondWith(new Instrumentation.ActivityResult(0, null));
        onView(withId(R.id.mallLocation3)).perform(click());
        intended(expectedIntent3);
    }

}