package com.example.kuniyakino.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuniyakino.MainPage;
import com.example.kuniyakino.R;
import com.example.kuniyakino.fragments.BookDetailFragment;
import com.example.kuniyakino.model.Book;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> implements Filterable {

    Context context;
    List<Book> booksList;
    List<Book> booksListFull;

    // Constructor of the customer Book Adapter
    public BookAdapter(Context context, List<Book> booksList) {
        this.context = context;
        this.booksList = booksList;
        booksListFull = new ArrayList<>(booksList);
    }

    @NonNull
    @NotNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.books_row, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BookAdapter.BookViewHolder holder, int position) {
        // Setup the view of each item on the RecyclerView
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        holder.bookImage.setImageResource(booksList.get(position).getImageURL());
        holder.bookTitle.setText(booksList.get(position).getTitle());
        holder.bookPrice.setText(booksList.get(position).getPrice());

        // Setup the click listener of each item selected within the RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the clickedbook data and create a new Book object
                Bundle savedData = new Bundle();
                Book clickedBook = new Book();
                clickedBook.setId(booksList.get(position).getId());
                clickedBook.setAuthor(booksList.get(position).getAuthor());
                clickedBook.setTitle(booksList.get(position).getTitle());
                clickedBook.setDescription(booksList.get(position).getDescription());
                clickedBook.setPrice(booksList.get(position).getPrice());
                clickedBook.setImageURL(booksList.get(position).getImageURL());
                clickedBook.setPage(booksList.get(position).getPage());
                clickedBook.setPublication(booksList.get(position).getPublication());
                clickedBook.setQuantity(booksList.get(position).getQuantity());

                // Serialize the clickedbook and put into bundle to pass to the next fragment
                savedData.putSerializable("book", clickedBook);
                bookDetailFragment.setArguments(savedData);

                // Change the fragment to the bookDetailFragment with clickedbook details in serialization
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.wrapper, bookDetailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    // Method to get the filter
    @Override
    public Filter getFilter() {
        return bookFilter;
    }

    // Setup the filter for the search view
    private Filter bookFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Book> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(booksListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Book item : booksListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            booksList.clear();
            booksList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public static final class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle, bookPrice;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.setImageURL);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookPrice = itemView.findViewById(R.id.bookPriceValue);
        }
    }
}