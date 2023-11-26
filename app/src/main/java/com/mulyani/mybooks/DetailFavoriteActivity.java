package com.mulyani.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mulyani.mybooks.Helper.FavoriteHelper;

/* displays detailed data from favorites */
public class DetailFavoriteActivity extends AppCompatActivity {

    private ImageView bookImageView;
    String bookTitle, bookContents, bookImage;
    private TextView bookContentsView;

    FavoriteHelper favoriteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fav_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getIntentExtra();

        getSupportActionBar().setTitle(bookTitle);
        initView();

        // db helper object creation
        favoriteHelper = new FavoriteHelper(this);

        bookContentsView.setText(bookContents);
        Glide.with(this).load(bookImage).into(bookImageView);

        // favorite floating action button
        FloatingActionButton fab = findViewById(R.id.floating_button);
        fab.setOnClickListener(view -> {

            favoriteHelper.deleteData(bookTitle);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        });
    }

    private void getIntentExtra() {
        bookTitle = getIntent().getExtras().getString("book_title");
        bookContents = getIntent().getExtras().getString("book_contents");
        bookImage = getIntent().getExtras().getString("book_image");
    }

    private void initView() {
        bookContentsView = findViewById(R.id.book_contents_favorite);
        bookImageView = findViewById(R.id.image_view_detail_image);
    }
}
