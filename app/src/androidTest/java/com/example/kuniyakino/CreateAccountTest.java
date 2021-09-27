package com.example.kuniyakino;

import android.os.IBinder;
import android.os.SystemClock;
import android.view.WindowManager;

import androidx.test.espresso.Root;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import junit.framework.TestCase;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
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
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

// Note. testCreateAccount() must be a new user each test!!

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateAccountTest {
    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);
    //public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initCondition(){
        onView(withId(R.id.createAccountBtn)).perform(click());
        intended(hasComponent(CreateAccount.class.getName()));
        SystemClock.sleep(2000);
    }

    @Test
    public void testCreateAccount() {

        // TEST VISIBILITY
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.inputName)).check(matches(isDisplayed()));
        onView(withId(R.id.inputEmailAddress)).check(matches(isDisplayed()));
        onView(withId(R.id.inputMobileNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.inputPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.inputPassword2)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewAccount)).check(matches(isDisplayed()));
        onView(withId(R.id.cancelCreateAccount)).check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.title)).check(matches(withText("Welcome To\nKuniyakino")));
        onView(withId(R.id.inputName)).check(matches(withHint("Name")));
        onView(withId(R.id.inputEmailAddress)).check(matches(withHint("Email Address")));
        onView(withId(R.id.inputMobileNumber)).check(matches(withHint("Mobile Number")));
        onView(withId(R.id.inputPassword)).check(matches(withHint("Password")));
        onView(withId(R.id.inputPassword2)).check(matches(withHint("Verify Password")));
        onView(withId(R.id.createNewAccount)).check(matches(withText("Create")));
        onView(withId(R.id.cancelCreateAccount)).check(matches(withText("Cancel")));

        //TEST USER INPUT
        String name = "Ed Lim";
        String email = "ed3@gmail.com";
        String mobileNumber = "98001234";
        String password = "12345678";
        String password2 = "12345678";

        onView(withId(R.id.inputName)).perform(typeText(name), pressImeActionButton());
        onView(withId(R.id.inputName)).check(matches(withText("Ed Lim")));
        onView(withId(R.id.inputEmailAddress)).perform(typeText(email), pressImeActionButton());
        onView(withId(R.id.inputEmailAddress)).check(matches(withText("ed3@gmail.com")));
        onView(withId(R.id.inputMobileNumber)).perform(typeText(mobileNumber), pressImeActionButton());
        onView(withId(R.id.inputMobileNumber)).check(matches(withText("98001234")));
        onView(withId(R.id.inputPassword)).perform(typeText(password), pressImeActionButton());
        onView(withId(R.id.inputPassword)).check(matches(withText("12345678")));
        onView(withId(R.id.inputPassword2)).perform(typeText(password2), pressImeActionButton());
        onView(withId(R.id.inputPassword2)).check(matches(withText("12345678")));

        onView(withId(R.id.createNewAccount)).perform(click());

        // VERIFY
        onView(withId(R.id.layout_activity_main))
                .check(matches(isDisplayed()));

        // CHECK TOAST MSG
        onView(withText("Account created.")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

       // SystemClock.sleep(2000);

    }

    @Test
    public void testCreateAccount_invalidEmail() {

        // TEST VISIBILITY
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.inputName)).check(matches(isDisplayed()));
        onView(withId(R.id.inputEmailAddress)).check(matches(isDisplayed()));
        onView(withId(R.id.inputMobileNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.inputPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.inputPassword2)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewAccount)).check(matches(isDisplayed()));
        onView(withId(R.id.cancelCreateAccount)).check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.title)).check(matches(withText("Welcome To\nKuniyakino")));
        onView(withId(R.id.inputName)).check(matches(withHint("Name")));
        onView(withId(R.id.inputEmailAddress)).check(matches(withHint("Email Address")));
        onView(withId(R.id.inputMobileNumber)).check(matches(withHint("Mobile Number")));
        onView(withId(R.id.inputPassword)).check(matches(withHint("Password")));
        onView(withId(R.id.inputPassword2)).check(matches(withHint("Verify Password")));
        onView(withId(R.id.createNewAccount)).check(matches(withText("Create")));
        onView(withId(R.id.cancelCreateAccount)).check(matches(withText("Cancel")));

        //TEST USER INPUT
        String name = "Albert Lim";
        String email = "albert_invalid_email";
        String mobileNumber = "98001234";
        String password = "12345678";
        String password2 = "12345678";

        onView(withId(R.id.inputName)).perform(typeText(name), pressImeActionButton());
        onView(withId(R.id.inputName)).check(matches(withText("Albert Lim")));
        onView(withId(R.id.inputEmailAddress)).perform(typeText(email), pressImeActionButton());
        onView(withId(R.id.inputEmailAddress)).check(matches(withText("albert_invalid_email")));
        onView(withId(R.id.inputMobileNumber)).perform(typeText(mobileNumber), pressImeActionButton());
        onView(withId(R.id.inputMobileNumber)).check(matches(withText("98001234")));
        onView(withId(R.id.inputPassword)).perform(typeText(password), pressImeActionButton());
        onView(withId(R.id.inputPassword)).check(matches(withText("12345678")));
        onView(withId(R.id.inputPassword2)).perform(typeText(password2), pressImeActionButton());
        onView(withId(R.id.inputPassword2)).check(matches(withText("12345678")));

        onView(withId(R.id.createNewAccount)).perform(click());

        // VERIFY
        // CHECK TOAST MSG
        onView(withText("Please enter a valid email address.")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        // SystemClock.sleep(2000);

    }

    @Test
    public void testCreateAccount_invalidPassword() {

        // TEST VISIBILITY
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.inputName)).check(matches(isDisplayed()));
        onView(withId(R.id.inputEmailAddress)).check(matches(isDisplayed()));
        onView(withId(R.id.inputMobileNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.inputPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.inputPassword2)).check(matches(isDisplayed()));
        onView(withId(R.id.createNewAccount)).check(matches(isDisplayed()));
        onView(withId(R.id.cancelCreateAccount)).check(matches(isDisplayed()));

        // TEST TEXT DISPLAY
        onView(withId(R.id.title)).check(matches(withText("Welcome To\nKuniyakino")));
        onView(withId(R.id.inputName)).check(matches(withHint("Name")));
        onView(withId(R.id.inputEmailAddress)).check(matches(withHint("Email Address")));
        onView(withId(R.id.inputMobileNumber)).check(matches(withHint("Mobile Number")));
        onView(withId(R.id.inputPassword)).check(matches(withHint("Password")));
        onView(withId(R.id.inputPassword2)).check(matches(withHint("Verify Password")));
        onView(withId(R.id.createNewAccount)).check(matches(withText("Create")));
        onView(withId(R.id.cancelCreateAccount)).check(matches(withText("Cancel")));

        //TEST USER INPUT
        String name = "Albert Lim";
        String email = "albert@gmail.com";
        String mobileNumber = "98001234";
        String password = "123456";
        String password2 = "1234invalid";

        onView(withId(R.id.inputName)).perform(typeText(name), pressImeActionButton());
        onView(withId(R.id.inputName)).check(matches(withText("Albert Lim")));
        onView(withId(R.id.inputEmailAddress)).perform(typeText(email), pressImeActionButton());
        onView(withId(R.id.inputEmailAddress)).check(matches(withText("albert@gmail.com")));
        onView(withId(R.id.inputMobileNumber)).perform(typeText(mobileNumber), pressImeActionButton());
        onView(withId(R.id.inputMobileNumber)).check(matches(withText("98001234")));
        onView(withId(R.id.inputPassword)).perform(typeText(password), pressImeActionButton());
        onView(withId(R.id.inputPassword)).check(matches(withText("123456")));
        onView(withId(R.id.inputPassword2)).perform(typeText(password2), pressImeActionButton());
        onView(withId(R.id.inputPassword2)).check(matches(withText("1234invalid")));

        onView(withId(R.id.createNewAccount)).perform(click());

        // VERIFY
        // CHECK TOAST MSG
        onView(withText("Passwords entered are not the same.")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        // SystemClock.sleep(2000);

    }

    @Test
    public void testCancelCreateAccount() {

        onView(withId(R.id.cancelCreateAccount)).perform(click());

        // VERIFY
        onView(withId(R.id.layout_activity_main))
                .check(matches(isDisplayed()));
    }

}

//http://www.qaautomated.com/2016/01/how-to-test-toast-message-using-espresso.html
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