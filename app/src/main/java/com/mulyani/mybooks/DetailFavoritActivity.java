package com.mulyani.mybooks;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mulyani.mybooks.Helper.FavoritHelper;

//menampilkan data detail yg dari favorit
public class DetailFavoritActivity extends AppCompatActivity {

    private ImageView gambarDetail;
    String judul, full_text, gambar;
    private TextView fullTextDetail;


    ContentValues values = new ContentValues();
    FavoritHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getIntentExtra();

        getSupportActionBar().setTitle(judul);
        initView();

        // pembuatan object dbhelper
        dbhelper = new FavoritHelper(this);

        fullTextDetail.setText(full_text);
        Glide.with(this).load(gambar).into(gambarDetail);

        //klo tombol hati diklik dihapus dari fav
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbhelper.deleteData(judul);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
    }

    private void getIntentExtra() {
        judul = getIntent().getExtras().getString("judul_cerpen");
        full_text = getIntent().getExtras().getString("full_text_cerpen");
        gambar = getIntent().getExtras().getString("image_cerpen");
    }

    private void initView() {
        fullTextDetail = findViewById(R.id.full_text_detail_fav);
        gambarDetail = findViewById(R.id.gambar_detail_fav);
    }
}
