package com.mulyani.mybooks;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mulyani.mybooks.Helper.FavoriteHelper;

/* Displays Detail */
public class DetailActivity extends AppCompatActivity {

    private ImageView bookImageView;
    String bookTitle, bookContents, bookImage;
    private TextView bookContentsView;

    ContentValues values = new ContentValues();
    FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getIntentExtra();

        getSupportActionBar().setTitle(bookTitle);
        initView();

        favoriteHelper = new FavoriteHelper(this);

        bookContentsView.setText(bookContents);
        Glide.with(this).load(bookImage).into(bookImageView);

        FloatingActionButton fab = findViewById(R.id.floating_button);
        fab.setOnClickListener(view -> {

            values.put("book_title", bookTitle);
            values.put("book_contents", bookContents);
            values.put("book_image", bookImage);

            favoriteHelper.addData(values);
            finish();
        });
    }

    private void getIntentExtra() {
        bookTitle = getIntent().getExtras().getString("book_title");
        bookContents = getIntent().getExtras().getString("book_contents");
        bookImage = getIntent().getExtras().getString("book_image");
    }

    private void initView() {
        bookContentsView = findViewById(R.id.book_contents_text_view);
        bookImageView = findViewById(R.id.image_view_book_image);
    }
}
