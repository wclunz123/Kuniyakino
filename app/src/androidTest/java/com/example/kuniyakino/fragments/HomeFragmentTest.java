package com.example.kuniyakino.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.kuniyakino.CreateAccount;
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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagKey;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.kuniyakino.Utils.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void initCondition(){
        onView(withId(R.id.inputLoginEmail)).perform(typeText("maxi@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputLoginPassword)).perform(typeText("12345678"), pressImeActionButton());

        onView(withId(R.id.loginButton)).perform(click());
        intended(hasComponent(MainPage.class.getName()));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.ic_home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
    }

    @Test
    public void testHome() {

        // TEST VISIBILITY
        onView(withId(R.id.backgroundAnimation)).check(matches(isDisplayed()));
        onView(withId(R.id.welcomeText)).check(matches(isDisplayed()));
        onView(withId(R.id.homePageViewPager)).check(matches(isDisplayed()));
        onView(withId(R.id.cart)).check(matches(isDisplayed()));
        // NOTE. NAVIGATION BUTTONS REFER TO MainPageTest.java

        // NOTE. TEST NAVIGATION REFERS TO MainPageTest

        // TEST TEXT DISPLAY
        onView(withId(R.id.welcomeText)).check(matches(withText("Welcome to\nKuniyakino")));

        // TEST IMAGE ANIMATION DISPLAY - BEYOND EXPRESSO'S SCOPE
        // NOTE. https://stackoverflow.com/questions/48534414/android-how-to-test-activity-transition-animation-with-espresso
        // onView(withId(R.id.backgroundAnimation)).check(matches(equalTo(R.id.animation_slideshow)));


    }

}
