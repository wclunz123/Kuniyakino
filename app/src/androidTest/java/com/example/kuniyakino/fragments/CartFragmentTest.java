package com.example.kuniyakino.fragments;

import android.database.MatrixCursor;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.kuniyakino.DBHelper;
import com.example.kuniyakino.MainActivity;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;
import com.example.kuniyakino.model.Book;
import com.google.android.material.textview.MaterialTextView;

import junit.framework.TestCase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.CursorMatchers.withRowString;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.kuniyakino.Utils.childAtPosition;
//import static com.example.kuniyakino.fragments.CartFragment.cartList;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;

// Note. Might need to clear cache for repeated test to ensure the matching of test total value
public class CartFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void initCondition(){
        onView(withId(R.id.inputLoginEmail)).perform(typeText("maxi@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputLoginPassword)).perform(typeText("12345678"), pressImeActionButton());

        onView(withId(R.id.loginButton)).perform(click());
        intended(hasComponent(MainPage.class.getName()));

        ViewInteraction searchButton = onView(
                allOf(withId(R.id.ic_search), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                1),
                        isDisplayed()));
        searchButton.perform(click());

        ViewInteraction book1 = onView(
                allOf(withId(R.id.bookRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)));
        book1.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction addToCart = onView(
                allOf(withId(R.id.addToCartBtn), withText("Add to Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                12)));
        addToCart.perform(scrollTo(), click());

        pressBack();

        ViewInteraction book2 = onView(
                allOf(withId(R.id.bookRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)));
        book2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction addToCart2 = onView(
                allOf(withId(R.id.addToCartBtn), withText("Add to Cart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                12)));
        addToCart2.perform(scrollTo(), click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.cartIcon),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        frameLayout.perform(click());
    }

    @Test
    public void testCart(){

        // TEST VISIBILITY
        onView(withId(R.id.cartRecycler)).check(matches(isDisplayed()));
        onView(withId(R.id.totalPrice)).check(matches(isDisplayed()));
        onView(withId(R.id.totalPriceValue)).check(matches(isDisplayed()));
        onView(withId(R.id.totalGST)).check(matches(isDisplayed()));
        onView(withId(R.id.totalGSTValue)).check(matches(isDisplayed()));
        onView(withId(R.id.checkOutBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.clearCartBtn)).check(matches(isDisplayed()));

        // VERIFY TOTAL AMOUNT (15.95 + 16)
        onView(withId(R.id.totalPriceValue)).check(matches(withText("31.95")));
        onView(withId(R.id.totalGSTValue)).check(matches(withText("2.24")));

        onView(withId(R.id.checkOutBtn)).perform(click());

        // VERIFY - CHECK TOAST MSG
        onView(withText("Thanks for shopping with us. Your item will be delivered within 3 working days.")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void testClearCart(){

        onView(withId(R.id.clearCartBtn)).perform(click());

        // VERIFY - CHECK TOAST MSG
        onView(withText("Cart has been cleared.")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        // VERIFY TOTAL AMOUNT (zero)
        onView(withId(R.id.totalPriceValue)).check(matches(withText("0.00")));
    }

}

