package com.example.kuniyakino;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.kuniyakino.fragments.AccountFragment;
import com.example.kuniyakino.fragments.CartFragment;
import com.example.kuniyakino.fragments.HomeFragment;
import com.example.kuniyakino.fragments.ProductFragment;
import com.example.kuniyakino.fragments.StoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    TextView cartBadgeTextView;
    DBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        dbHelper = new DBHelper(getApplicationContext());

        HomeFragment home = new HomeFragment();
        ProductFragment search = new ProductFragment();
        StoreFragment store = new StoreFragment();
        AccountFragment account = new AccountFragment();
        CartFragment cart = new CartFragment();

        // Home fragment will show upon login
        makeCurrentFragment(home);

        // Set the bottom navigation on selected listener to switch to different fragments
        BottomNavigationView bottom_nav = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ic_home:
                        makeCurrentFragment(home);
                        return true;
                    case R.id.ic_search:
                        makeCurrentFragment(search);
                        return true;
                    case R.id.ic_location:
                        makeCurrentFragment(store);
                        return true;
                    case R.id.ic_account:
                        makeCurrentFragment(account);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Configure the action bar with cart icon
        getMenuInflater().inflate(R.menu.nav_cart, menu);
        MenuItem menuItem = menu.findItem(R.id.cartIcon);
        View actionView = menuItem.getActionView();
        cartBadgeTextView = actionView.findViewById(R.id.cart_badge_text_view);

        // Calculate the cart count item and display on cart badge
        DBHelper dbHelper = new DBHelper(this);
        int cartCount = dbHelper.getCartCount(getCurrentUser());
        updateCartBadgeView(cartCount); // Update the cart badge count

        // On click listener for the cart icon on the action bar
        CartFragment cart = new CartFragment();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCurrentFragment(cart);
            }
        });

        return true;
    }

    // Method to update the cart badge count
    public void updateCartBadgeView(int cartCount) {
        if (cartCount == 0) {
            cartBadgeTextView.setVisibility(View.INVISIBLE);
        } else {
            cartBadgeTextView.setVisibility(View.VISIBLE);
            cartBadgeTextView.setText(String.valueOf(cartCount));
        }
    }

    // Method to replace the fragment with new fragment
    private void makeCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.wrapper, fragment).commit();
    }

    // Get the current user session
    public String getCurrentUser() {
        Intent myIntent = getIntent();
        String currentUser = myIntent.getStringExtra("CurrentUser");
        return currentUser;
    }
}
