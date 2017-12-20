package com.mulyani.mybooks;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulyani.mybooks.Adapter.BukuAdapter;
import com.mulyani.mybooks.Model.ModelBuku;
import com.mulyani.mybooks.R;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * A simple {@link Fragment} subclass.
 */

//menampilkan fragment home
public class HomeFragment extends Fragment {


    private Spinner spKategori;
    private Button btnCek;
    private ListView mainListView;
    private int pilih;

    List<ModelBuku> listBuku;

    private DatabaseReference dbBuku;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);

        //Slider Image
        BannerSlider bannerSlider = view.findViewById(R.id.banner_slider1);
        List<Banner> banners = new ArrayList<>();

        banners.add(new DrawableBanner(R.drawable.images));
        banners.add(new DrawableBanner(R.drawable.cinta));
        banners.add(new DrawableBanner(R.drawable.cintaimage));
        bannerSlider.setBanners(banners);

        spKategori = view.findViewById(R.id.sp_kategori);
        btnCek = view.findViewById(R.id.btnCek);
        mainListView = view.findViewById(R.id.main_list_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_kategori, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKategori.setAdapter(adapter);

        listBuku = new ArrayList<>();
        dbBuku = FirebaseDatabase.getInstance().getReference();




        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kategori = spKategori.getSelectedItem().toString();

                if (kategori.equals("Horror")){
                   pilih = 1;
                }else if (kategori.equals("Romance")){
                    pilih = 2;
                }

                getDataCerpen(pilih);

            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelBuku Book= listBuku.get(position);
                Intent intent = new Intent(getContext(),  DetailActivity.class);
                intent.putExtra("judul_cerpen", Book.getJudul_cerpen());
                intent.putExtra("full_text_cerpen",Book.getFull_text_cerpen());
                intent.putExtra("image_cerpen", Book.getImage_cerpen());
                startActivity(intent);
            }
        });
        return view;
    }

    private void getDataCerpen(final int pilih) {
        // Menampilkan loading
        final ProgressDialog loading = new ProgressDialog(getContext());
        loading.setTitle("Loading");
        loading.setMessage("Cek LIst Cerpen");
        loading.setCancelable(false);
        loading.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        loading.show();


//        dbBuku = FirebaseDatabase.getInstance().getReference();
        dbBuku.child("DataBuku")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listBuku.clear();           // listBuku -> arraylist
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            ModelBuku buku = postSnapshot.getValue(ModelBuku.class);
                            if (buku.getJenis_cerpen() == pilih) {
                                listBuku.add(buku);
                            }

                        }
                        loading.hide();
                        BukuAdapter adapter = new BukuAdapter(getActivity(), listBuku);
                        mainListView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Data Eror", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
