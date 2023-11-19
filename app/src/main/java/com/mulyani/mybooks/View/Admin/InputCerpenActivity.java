package com.mulyani.mybooks.View.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mulyani.mybooks.Model.ModelBuku;
import com.mulyani.mybooks.R;

//untuk membuat cerpen
public class InputCerpenActivity extends AppCompatActivity {


    //firebase database & storage
    private DatabaseReference dbCerpen;

    ProgressDialog progressDialog;
    private EditText editTextKategori;
    private EditText editTextJudul;
    private EditText editTextTahun;
    private EditText editTextPengarang;
    private EditText editTextHarga;
    private EditText editTextJumlah;
    private EditText editTextSinopsis;
    private EditText editTextFullText;
    private EditText editTextGambar;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_buku);


        initView();

        //memasukkan data ke firebase
        dbCerpen = FirebaseDatabase.getInstance().getReference("DataBuku");

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahCerpen();
            }
        });
    }

    private void tambahCerpen() {

        editTextKategori = findViewById(R.id.editTextKategori);
        editTextJudul = findViewById(R.id.editTextJudul);
        editTextTahun = findViewById(R.id.editTextTahun);
        editTextPengarang = findViewById(R.id.editTextPengarang);
        editTextHarga = findViewById(R.id.editTextHarga);
        editTextJumlah = findViewById(R.id.editTextJumlah);
        editTextSinopsis = findViewById(R.id.editTextSinopsis);
        editTextFullText = findViewById(R.id.editTextFullText);
        editTextGambar = findViewById(R.id.editTextGambar);

        //mengambil inputan
        int Kategori = Integer.parseInt(editTextKategori.getText().toString());
        String Judul = editTextJudul.getText().toString();
        String Tahun = editTextTahun.getText().toString();
        String Pengarang = editTextPengarang.getText().toString();
        String Harga = editTextHarga.getText().toString();
        String Jumlah = editTextJumlah.getText().toString();
        String Sinopsis = editTextSinopsis.getText().toString();
        String FullText = editTextFullText.getText().toString();
        String Gambar = editTextGambar.getText().toString();


        //id unik
        String id_cerpen = dbCerpen.push().getKey();
        ModelBuku buku = new ModelBuku(id_cerpen, Judul, Pengarang, Tahun, Kategori,
                Sinopsis,FullText, Gambar, Harga, Jumlah);

        //memasukan nilai ke db
        dbCerpen.child(id_cerpen).setValue(buku);
        reCreate();

    }

    private void reCreate() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void initView() {

        progressDialog = new ProgressDialog(InputCerpenActivity.this);
        editTextKategori = findViewById(R.id.editTextKategori);
        editTextJudul = findViewById(R.id.editTextJudul);
        editTextTahun = findViewById(R.id.editTextTahun);
        editTextPengarang = findViewById(R.id.editTextPengarang);
        editTextHarga = findViewById(R.id.editTextHarga);
        editTextJumlah = findViewById(R.id.editTextJumlah);
        editTextSinopsis = findViewById(R.id.editTextSinopsis);
        editTextFullText = findViewById(R.id.editTextFullText);
        editTextGambar = findViewById(R.id.editTextGambar);
        buttonAdd = findViewById(R.id.buttonAdd);
    }
}
