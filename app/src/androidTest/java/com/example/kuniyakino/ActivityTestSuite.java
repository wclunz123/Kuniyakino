package com.example.kuniyakino;

import com.example.kuniyakino.fragments.AccountFragmentTest;
import com.example.kuniyakino.fragments.BookDetailFragmentTest;
import com.example.kuniyakino.fragments.CartFragmentTest;
import com.example.kuniyakino.fragments.HomeFragmentTest;
import com.example.kuniyakino.fragments.ProductFragmentTest;
import com.example.kuniyakino.fragments.StoreFragmentTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Note. Test user email: maxi@gmail.com & Test user password: 12345678

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                MainActivityTest.class,
                // Note. testCreateAccount() must be a new user each test
                CreateAccountTest.class,
                MainPageTest.class,
                CartFragmentTest.class,
                AccountFragmentTest.class,
                StoreFragmentTest.class,
                HomeFragmentTest.class,
                ProductFragmentTest.class,
                BookDetailFragmentTest.class
        })

public class ActivityTestSuite {
}
