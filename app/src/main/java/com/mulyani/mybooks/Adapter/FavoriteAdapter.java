package com.mulyani.mybooks.Adapter;

import android.content.Context;
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

import java.util.ArrayList;

/**
 * Created by Mulyani on 12/19/17.
 */

/* displays data from db to favorites */
public class FavoriteAdapter extends ArrayAdapter<BookModel> {
    private final Context context;
    int mResource;

    /**
     *
     * @param context context
     * @param resource resource
     * @param objects objects
     */
    public FavoriteAdapter(Context context, int resource, ArrayList<BookModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        String bookTitle = getItem(position).getBookTitle();
        String bookImage = getItem(position).getBookImage();
        String bookContents = getItem(position).getBookContents();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource, parent,false);

        ImageView bookImageView = convertView.findViewById(R.id.book_image_image_view);
        TextView bookTitleView = convertView.findViewById(R.id.book_title_fav);
        TextView bookContentsView = convertView.findViewById(R.id.book_contents_full);

        bookTitleView.setText(bookTitle);
        bookContentsView.setText(bookContents);

        // Display Image
        Glide.with(convertView).load(bookImage).into(bookImageView);

        return convertView;
    }
}
