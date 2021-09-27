package com.example.kuniyakino.fragments;

import android.os.SystemClock;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.kuniyakino.MainActivity;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;

import junit.framework.TestCase;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.kuniyakino.Utils.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProductFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);
    //public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initCondition(){
        onView(withId(R.id.inputLoginEmail)).perform(typeText("maxi@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputLoginPassword)).perform(typeText("12345678"), pressImeActionButton());

        onView(withId(R.id.loginButton)).perform(click());
        intended(hasComponent(MainPage.class.getName()));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.ic_search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
    }
    @Test
    public void searchTest(){
        // SEARCH BAR
        ViewInteraction searchBar = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));

        // TEST TEXT DISPLAY
        searchBar.check(matches(withHint("Search by category, book title, publications..")));

        // TEST USER INPUT INTO SEARCH BAR
        searchBar.perform(replaceText("ambition"), closeSoftKeyboard());

        // TEST VISIBILITY
        searchBar.check(matches(isDisplayed()));
        onView(withId(R.id.bookRecycler)).check(matches(isDisplayed()));

        // TEST SEARCH ITEM DISPLAYED
        ViewInteraction imageView = onView(
                allOf(withId(R.id.setImageURL),
                        withParent(withParent(withId(R.id.bookRecycler))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.bookTitle), withText("ambition"),
                        withParent(withParent(withId(R.id.bookRecycler))),
                        isDisplayed()));
        textView.check(matches(withText("ambition")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.bookPriceValue), withText("15.95"),
                        withParent(withParent(withId(R.id.bookRecycler))),
                        isDisplayed()));
        textView2.check(matches(withText("15.95")));

        // TEST USER CLICK ON ITEMS DISPLAYED
        onView(withId(R.id.bookRecycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // VERIFY
        onView(withId(R.id.layout_fragment_book_detail))
                .check(matches(isDisplayed()));
        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.setImageURL),
                        withParent(withParent(withId(R.id.layout_fragment_book_detail))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.bookTitle), withText("ambition"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText("ambition")));
    }

    @Test
    public void searchTest_invalidCondition() {
        // SEARCH BAR
        ViewInteraction searchBar = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));

        // TEST TEXT DISPLAY
        searchBar.check(matches(withHint("Search by category, book title, publications..")));

        // TEST USER INPUT INTO SEARCH BAR
        searchBar.perform(replaceText("obama"), closeSoftKeyboard());

        // TEST SEARCH ITEM DISPLAYED
        ViewInteraction imageView = onView(
                allOf(withId(R.id.setImageURL),
                        withParent(withParent(withId(R.id.bookRecycler))),
                        isDisplayed()));
        imageView.check(doesNotExist());

    }
}