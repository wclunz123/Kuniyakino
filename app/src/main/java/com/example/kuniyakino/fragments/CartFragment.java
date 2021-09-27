package com.example.kuniyakino.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuniyakino.DBHelper;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;
import com.example.kuniyakino.adapter.BookAdapter;
import com.example.kuniyakino.adapter.CartAdapter;
import com.example.kuniyakino.model.Book;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    RecyclerView cartItemRecycler;
    CartAdapter cartAdapter;
    List<Book> cartList;
    static double totalPrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        DBHelper dbHelper = new DBHelper(getContext());

        // Getting view of cart price
        TextView totalCartPrice = getView().findViewById(R.id.totalPriceValue);
        TextView totalCartGstPrice = getView().findViewById(R.id.totalGSTValue);
        TextView noItemText = getView().findViewById(R.id.noItemTextView);

        String currentUser = ((MainPage)getActivity()).getCurrentUser();
        cartList = dbHelper.getCartData(currentUser);

        // Check if cart is empty and display message
        if (cartList.size()!=0) {
            noItemText.setVisibility(View.INVISIBLE);
        } else {
            noItemText.setVisibility(View.VISIBLE);
        }
        updateCartValue(totalCartPrice, totalCartGstPrice);
        setCartItemRecycler(cartList);

        // Configure checkout button listener
        Button checkOutBtn = getView().findViewById(R.id.checkOutBtn);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartList.size()>0) {
                    cartList.clear();
                    dbHelper.deleteAllCartData(currentUser);
                    cartItemRecycler.getAdapter().notifyItemRangeRemoved(0, cartList.size());
                    cartItemRecycler.setVisibility(View.INVISIBLE);
                    updateCartValue(totalCartPrice, totalCartGstPrice);
                    //Update the cart badge count
                    int cartCount = dbHelper.getCartCount(currentUser);
                    ((MainPage)getActivity()).updateCartBadgeView(cartCount);
                    displayToast("Thanks for shopping with us. Your item will be delivered within 3 working days.");
                } else {
                    displayToast("You have not added any item in your cart.");
                }

            }
        });

        Button clearCartBtn = getView().findViewById(R.id.clearCartBtn);
        clearCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartList.size()>0) {
                    cartList.clear();
                    dbHelper.deleteAllCartData(currentUser); //delete all the cart item of the user
                    //Update the cart badge count
                    int cartCount = dbHelper.getCartCount(currentUser);
                    ((MainPage)getActivity()).updateCartBadgeView(cartCount);
                    cartItemRecycler.getAdapter().notifyItemRangeRemoved(0, cartList.size());
                    cartItemRecycler.setVisibility(View.INVISIBLE);
                    updateCartValue(totalCartPrice, totalCartGstPrice);
                    displayToast("Cart has been cleared.");
                } else {
                    displayToast("You have not added any item in your cart.");
                }

            }
        });

        cartItemRecycler.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                updateCartValue(totalCartPrice, totalCartGstPrice);

                DBHelper dbHelper = new DBHelper(getContext());
                int cartCount = dbHelper.getCartCount(currentUser);
                ((MainPage)getActivity()).updateCartBadgeView(cartCount);
            }
        });
    }

    // Method used to update the cart total value
    public void updateCartValue(TextView totalCartPrice, TextView totalCartGstPrice) {
        totalPrice = calculateCartTotalPrice(cartList);
        DecimalFormat f = new DecimalFormat("#0.00");
        totalCartPrice.setText(f.format(Math.round(totalPrice*100.0)/100.0));
        totalCartGstPrice.setText(f.format(Math.round((totalPrice*0.07)*100.0)/100.0));
    }

    // Method used to calculate the total price
    private double calculateCartTotalPrice(List<Book> cartList) {
        double totalPrice = 0;

        if (cartList.isEmpty()) {
            totalPrice = 0;
        } else {
            for (int i = 0; i < cartList.size(); i++) {
                totalPrice += Double.parseDouble(cartList.get(i).getPrice());
            }
        }

        return totalPrice;
    }

    // Setup the Cart RecyclerView by applying LayoutManager and setting customer Adapter
    private void setCartItemRecycler(List<Book> cartList) {

        cartItemRecycler = (RecyclerView)getView().findViewById(R.id.cartRecycler);
        // Setup the layout manager of the cart's RecyclerView to linear layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        cartItemRecycler.setLayoutManager(layoutManager);

        // Setup the customer adapter to the RecyclerView
        String currentUser = ((MainPage)getActivity()).getCurrentUser();
        cartAdapter = new CartAdapter(getContext(), cartList, currentUser);
        cartItemRecycler.setAdapter(cartAdapter);
    }

    // Method used to display toast message
    private void displayToast(String message) {
        Toast toast = Toast.makeText(getContext(),
                message,
                Toast.LENGTH_LONG);
        toast.show();
    }

}