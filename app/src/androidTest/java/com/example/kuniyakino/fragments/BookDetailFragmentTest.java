package com.example.kuniyakino.fragments;

import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.WindowManager;

import androidx.test.espresso.Root;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.kuniyakino.MainActivity;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;

import junit.framework.TestCase;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.meta.When;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.kuniyakino.Utils.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BookDetailFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);

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

        ViewInteraction searchBook1 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchBook1.perform(replaceText("ambition"), closeSoftKeyboard());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.bookRecycler),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testBookDetails() {

        // TEST VISIBILITY
        onView(withId(R.id.setImageURL)).check(matches(isDisplayed()));
        onView(withId(R.id.bookTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.bookPrice)).check(matches(isDisplayed()));
        onView(withId(R.id.bookDescription)).check(matches(isDisplayed()));
        onView(withId(R.id.bookAuthor)).check(matches(isDisplayed()));
        onView(withId(R.id.bookAuthorName)).check(matches(isDisplayed()));
        onView(withId(R.id.bookPublisher)).check(matches(isDisplayed()));
        onView(withId(R.id.bookPublisherName)).check(matches(isDisplayed()));
        onView(withId(R.id.bookPage)).check(matches(isDisplayed()));
        onView(withId(R.id.bookPageNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.bookQty))
                .perform(ViewActions.scrollTo())
                .check(matches(isDisplayed()));
        onView(withId(R.id.bookQtyNumber))
                .perform(ViewActions.scrollTo())
                .check(matches(isDisplayed()));
        onView(withId(R.id.addToCartBtn))
                .perform(ViewActions.scrollTo())
                .check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.bookTitle)).check(matches(withText("ambition")));
        onView(withId(R.id.bookPrice)).check(matches(withText("15.95")));
        onView(withId(R.id.bookDescription)).check(matches(withText("American society struggles to recover from an eruption at the Yellowstone super caldera that left millions dead and forced those alive to migrate east to avoid starvation when crops failed. The Reclamation Party, lead by charismatic Senator Jaxson Blake, promises to restore prosperity. When reporter Jasmine Blare uncovers a government program that removes undesirables from the population, she must find a way to stop the slaughter, even if it destroys Jaxson, the only man she has ever loved.")));
        onView(withId(R.id.bookAuthor)).check(matches(withText("Author: ")));
        onView(withId(R.id.bookAuthorName)).check(matches(withText("Seneca Fauls")));
        onView(withId(R.id.bookPublisher)).check(matches(withText("Publication: ")));
        onView(withId(R.id.bookPublisherName)).check(matches(withText("Peril")));
        onView(withId(R.id.bookPage)).check(matches(withText("Pages: ")));
        onView(withId(R.id.bookPageNumber)).check(matches(withText("230")));
        onView(withId(R.id.bookQty)).check(matches(withText("Quantity: ")));
        onView(withId(R.id.bookQtyNumber)).check(matches(withText("79")));
        onView(withId(R.id.addToCartBtn)).check(matches(withText("Add to Cart")));


        //TEST ACTIVITY NAVIGATION
        onView(withId(R.id.addToCartBtn))
                .perform(ViewActions.scrollTo())
                .perform(click());

        // VERIFY
        // CHECK TOAST MSG
        onView(withText("Added into Cart.")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));


    }
}

class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override
    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                return true;
            }
        }
        return false;
    }
}