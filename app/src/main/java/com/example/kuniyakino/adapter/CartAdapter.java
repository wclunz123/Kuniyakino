package com.example.kuniyakino.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuniyakino.DBHelper;
import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;
import com.example.kuniyakino.fragments.BookDetailFragment;
import com.example.kuniyakino.fragments.CartFragment;
import com.example.kuniyakino.model.Book;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<Book> cartList;
    String currentUser;
    Book clickedBook;
    DBHelper dbHelper;

    // Constructor of the custom Cart Adapter
    public CartAdapter(Context context, List<Book> cartList, String currentUser) {
        this.context = context;
        this.cartList = cartList;
        this.currentUser = currentUser;
    }

    @NonNull
    @NotNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_row, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartAdapter.CartViewHolder holder, int position) {

        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle savedData = new Bundle();
        clickedBook = new Book();

        // Setup each item view on the Recycler view
        if (!cartList.isEmpty()) {
            holder.bookImage.setImageResource(cartList.get(position).getImageURL());
            holder.bookTitle.setText(cartList.get(position).getTitle());
            holder.bookAuthor.setText(cartList.get(position).getAuthor());
            holder.bookPublication.setText(cartList.get(position).getPublication());
            holder.bookPrice.setText(cartList.get(position).getPrice());
        }

        // Setup on click listener of every cart items clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedBook.setId(cartList.get(position).getId());
                clickedBook.setAuthor(cartList.get(position).getAuthor());
                clickedBook.setTitle(cartList.get(position).getTitle());
                clickedBook.setDescription(cartList.get(position).getDescription());
                clickedBook.setPrice(cartList.get(position).getPrice());
                clickedBook.setImageURL(cartList.get(position).getImageURL());
                clickedBook.setPage(cartList.get(position).getPage());
                clickedBook.setPublication(cartList.get(position).getPublication());
                clickedBook.setQuantity(cartList.get(position).getQuantity());

                // Serialized the clickedbook details into bundle and pass to the next fragment
                savedData.putSerializable("book", clickedBook);
                bookDetailFragment.setArguments(savedData);

                // Replace current fragment to the bookDetailFragment and display the clickedbook details
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.wrapper, bookDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Setup the delete item button with 'X' icon on click listener
        dbHelper = new DBHelper(context.getApplicationContext());
        holder.deleteCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteCartData(currentUser, cartList.get(position).getId()); // Remove cart item from database
                cartList.remove(cartList.get(position)); // Remove the item from the variable
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,cartList.size());
                holder.itemView.setVisibility(v.GONE); // Delete the item from the RecyclerView
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static final class CartViewHolder extends RecyclerView.ViewHolder {
        // Get all the Android components for the cart item
        ImageView bookImage;
        TextView bookTitle, bookPublication, bookAuthor, bookPrice;
        ImageButton deleteCartItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.setImageURL);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthorName);
            bookPublication = itemView.findViewById(R.id.bookPublisherName);
            bookPrice = itemView.findViewById(R.id.bookPriceValue);
            deleteCartItem = itemView.findViewById(R.id.deleteCartItemBtn);
        }
    }
}
