package com.mulyani.mybooks.View.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulyani.mybooks.Model.BookModel;
import com.mulyani.mybooks.R;

/* input book */
public class InputBookActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    ProgressDialog progressDialog;
    private EditText editTextBookType;
    private EditText editTextBookTitle;
    private EditText editTextBookYear;
    private EditText editTextBookAuthor;
    private EditText editTextBookPrice;
    private EditText editTextBookAmount;
    private EditText editTextBookSynopsis;
    private EditText editTextBookContents;
    private EditText editTextBookImage;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_input_activity);

        initView();

        databaseReference = FirebaseDatabase.getInstance().getReference("DataBuku");

        addButton.setOnClickListener(v -> addBook());
    }

    private void addBook() {

        editTextBookType = findViewById(R.id.book_type);
        editTextBookTitle = findViewById(R.id.book_title);
        editTextBookYear = findViewById(R.id.book_year);
        editTextBookAuthor = findViewById(R.id.book_author);
        editTextBookPrice = findViewById(R.id.book_price);
        editTextBookAmount = findViewById(R.id.word_counts);
        editTextBookSynopsis = findViewById(R.id.book_synopsis);
        editTextBookContents = findViewById(R.id.book_contents);
        editTextBookImage = findViewById(R.id.book_image);

        int bookType = Integer.parseInt(editTextBookType.getText().toString());
        String bookTitle = editTextBookTitle.getText().toString();
        String bookYear = editTextBookYear.getText().toString();
        String bookAuthor = editTextBookAuthor.getText().toString();
        String bookPrice = editTextBookPrice.getText().toString();
        String bookAmount = editTextBookAmount.getText().toString();
        String bookSynopsis = editTextBookSynopsis.getText().toString();
        String bookContents = editTextBookContents.getText().toString();
        String bookImage = editTextBookImage.getText().toString();
        String bookId = databaseReference.push().getKey();
        BookModel bookModel = new BookModel(bookId, bookTitle, bookAuthor, bookYear, bookType,
                bookSynopsis, bookContents, bookImage, bookPrice, bookAmount);

        databaseReference.child(bookId).setValue(bookModel);
        reCreate();
    }

    private void reCreate() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void initView() {
        progressDialog = new ProgressDialog(InputBookActivity.this);
        editTextBookType = findViewById(R.id.book_type);
        editTextBookTitle = findViewById(R.id.book_title);
        editTextBookYear = findViewById(R.id.book_year);
        editTextBookAuthor = findViewById(R.id.book_author);
        editTextBookPrice = findViewById(R.id.book_price);
        editTextBookAmount = findViewById(R.id.word_counts);
        editTextBookSynopsis = findViewById(R.id.book_synopsis);
        editTextBookContents = findViewById(R.id.book_contents);
        editTextBookImage = findViewById(R.id.book_image);
        addButton = findViewById(R.id.addButton);
    }
}
