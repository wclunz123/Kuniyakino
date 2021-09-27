package com.example.kuniyakino.fragments;

import android.os.SystemClock;

import androidx.annotation.ContentView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
//import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.example.kuniyakino.MainActivity;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
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


@RunWith(AndroidJUnit4.class)
public class AccountFragmentTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void initCondition() {
        onView(withId(R.id.inputLoginEmail)).perform(typeText("maxi@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.inputLoginPassword)).perform(typeText("12345678"), pressImeActionButton());

        onView(withId(R.id.loginButton)).perform(click());
        intended(hasComponent(MainPage.class.getName()));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.ic_account), withContentDescription("Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());
    }

    @Test
    public void testUpdateAccount() {

        // TEST VISIBILITY
        onView(withId(R.id.inputName)).check(matches(isDisplayed()));
        onView(withId(R.id.inputMobileNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.addressLine1)).check(matches(isDisplayed()));
        onView(withId(R.id.addressLine2)).check(matches(isDisplayed()));
        onView(withId(R.id.paymentMethod)).check(matches(isDisplayed()));
        onView(withId(R.id.cardNo)).check(matches(isDisplayed()));
        onView(withId(R.id.updateAccount)).check(matches(isDisplayed()));
        onView(withId(R.id.logoutButton)).check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.inputName)).check(matches(withHint("Name")));
        onView(withId(R.id.inputMobileNumber)).check(matches(withHint("Mobile Number")));
        onView(withId(R.id.addressLine1)).check(matches(withHint("Address Line 1")));
        onView(withId(R.id.addressLine2)).check(matches(withHint("Address Line 2")));
        onView(withId(R.id.paymentMethod)).check(matches(withHint("Payment Method")));
        onView(withId(R.id.cardNo)).check(matches(withHint("Card No")));
        onView(withId(R.id.updateAccount)).check(matches(withText("Update")));
        onView(withId(R.id.logoutButton)).check(matches(withText("Logout")));

        //TEST USER INPUT
        String name = "Maxi Lim";
        String mobileNumber = "98001234";
        String addressLine1 = "Blk 888 Toa Payoh Ave 1";
        String addressLine2 = "56800 Toa Payoh";
        String paymentMethod = "Visa";
        String cardNo = "555544448888";

        onView(withId(R.id.inputName)).perform(replaceText(name), pressImeActionButton());
        onView(withId(R.id.inputName)).check(matches(withText("Maxi Lim")));
        onView(withId(R.id.inputMobileNumber)).perform(replaceText(mobileNumber), pressImeActionButton());
        onView(withId(R.id.inputMobileNumber)).check(matches(withText("98001234")));
        onView(withId(R.id.addressLine1)).perform(replaceText(addressLine1), pressImeActionButton());
        onView(withId(R.id.addressLine1)).check(matches(withText("Blk 888 Toa Payoh Ave 1")));
        onView(withId(R.id.addressLine2)).perform(replaceText(addressLine2), pressImeActionButton());
        onView(withId(R.id.addressLine2)).check(matches(withText("56800 Toa Payoh")));
        onView(withId(R.id.paymentMethod)).perform(replaceText(paymentMethod), pressImeActionButton());
        onView(withId(R.id.paymentMethod)).check(matches(withText("Visa")));
        onView(withId(R.id.cardNo)).perform(replaceText(cardNo), closeSoftKeyboard());
        onView(withId(R.id.cardNo)).check(matches(withText("555544448888")));


        onView(withId(R.id.updateAccount)).perform(click());

        // VERIFY
        // CHECK TOAST MSG
        onView(withText("Account Update Success!")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        // VERIFY
        onView(withId(R.id.layout_main_page))
                .check(matches(isDisplayed()));

        //SystemClock.sleep(2000);
    }

    @Test
    public void testLogout() {

        // TEST NAVIGATION FROM LOGOUT
        onView(withId(R.id.logoutButton)).perform(click());

        // VERIFY
        onView(withId(R.id.layout_activity_main))
                .check(matches(isDisplayed()));
    }

}