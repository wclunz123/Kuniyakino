package com.example.kuniyakino.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuniyakino.DBHelper;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;
import com.example.kuniyakino.model.Book;

public class BookDetailFragment extends Fragment {

    Book clickedBook;
    CartFragment cartFragment;
    Button addToCartBtn;
    ImageView imageURL;
    TextView bookTitle, bookDescription, bookAuthor, bookPublisher, bookPrice, bookQty, bookPage;
    DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null) {
            // Get serializable from the RecyclerView of the ProductFragment
            clickedBook = (Book)getArguments().getSerializable("book");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        cartFragment = new CartFragment();
        dbHelper = new DBHelper(getContext());

        // Get the TextView components from the XML file
        imageURL = getView().findViewById(R.id.setImageURL);
        bookTitle = getView().findViewById(R.id.bookTitle);
        bookDescription = getView().findViewById(R.id.bookDescription);
        bookAuthor = getView().findViewById(R.id.bookAuthorName);
        bookPublisher = getView().findViewById(R.id.bookPublisherName);
        bookPrice = getView().findViewById(R.id.bookPrice);
        bookQty = getView().findViewById(R.id.bookQtyNumber);
        bookPage = getView().findViewById(R.id.bookPageNumber);
        addToCartBtn = getView().findViewById(R.id.addToCartBtn);

        // Set the TextView components to the clickedbook details passed from the ProductFragment RecyclerView
        // through serialization
        bookTitle.setText(clickedBook.getTitle());
        bookDescription.setText(clickedBook.getDescription());
        bookAuthor.setText(clickedBook.getAuthor());
        bookPublisher.setText(clickedBook.getPublication());
        bookPrice.setText(clickedBook.getPrice());
        bookPage.setText(String.valueOf(clickedBook.getPage()));
        bookQty.setText(String.valueOf(clickedBook.getQuantity()));
        imageURL.setImageResource(clickedBook.getImageURL());

        // Setup on click listener for Add to cart button
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putSerializable("cart", clickedBook); // Serialized the clickedbook data
                cartFragment.setArguments(data);
                String currentUser = ((MainPage)getActivity()).getCurrentUser();

                // Insert the cart data into the database
                dbHelper.insertCartData(currentUser, clickedBook.getId());

                // Update the cart badge count
                int cartCount = dbHelper.getCartCount(currentUser);
                ((MainPage)getActivity()).updateCartBadgeView(cartCount);

                // Display add to cart success message
                Toast toast = Toast.makeText(getContext(),
                        "Added into Cart.",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}