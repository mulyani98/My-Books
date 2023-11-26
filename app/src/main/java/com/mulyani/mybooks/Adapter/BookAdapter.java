package com.mulyani.mybooks.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.mulyani.mybooks.Model.BookModel;
import com.mulyani.mybooks.R;

import java.util.List;

/* displays data taken from the DB to the list in the home fragment */
public class BookAdapter extends ArrayAdapter<BookModel> {
    public Activity activity;
    List<BookModel> bookList;

    public BookAdapter(Activity activity, List<BookModel> bookList) {
        super(activity, R.layout.book_list, bookList);
        this.activity = activity;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.book_list, null, true);

        ImageView bookCover = listViewItem.findViewById(R.id.book_image_view);
        TextView bookTitle = listViewItem.findViewById(R.id.book_title_view);
        TextView bookType = listViewItem.findViewById(R.id.book_type_text_view);
        TextView bookPublishYear = listViewItem.findViewById(R.id.book_year_text_view);
        TextView bookSynopsis = listViewItem.findViewById(R.id.book_synopsis_text_view);
        TextView bookPrice = listViewItem.findViewById(R.id.book_price_view);

        BookModel book = bookList.get(position);
        Glide.with(listViewItem).load(book.getBookImage()).into(bookCover);
        bookTitle.setText(book.getBookTitle());
        bookType.setText(String.valueOf(book.getBookType()));
        bookPublishYear.setText(String.valueOf(book.getBookYear()));
        bookSynopsis.setText(book.getBookSynopsis());
        bookPrice.setText(String.valueOf(book.getBookPrice()));

        return listViewItem;
    }
}
