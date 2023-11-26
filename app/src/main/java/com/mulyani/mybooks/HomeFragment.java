package com.mulyani.mybooks;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mulyani.mybooks.Adapter.BookAdapter;
import com.mulyani.mybooks.Model.BookModel;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * A simple {@link Fragment} subclass.
 */

/* displays Home Fragments */
public class HomeFragment extends Fragment {
    private Spinner spinnerBookType;
    private ListView listView;
    private int selectItem;

    List<BookModel> bookModelList;

    private DatabaseReference databaseReference;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        // Slider Image
        BannerSlider bannerSlider = view.findViewById(R.id.slider_banner);
        List<Banner> banners = new ArrayList<>();

        banners.add(new DrawableBanner(R.drawable.banner_1));
        banners.add(new DrawableBanner(R.drawable.banner_2));
        banners.add(new DrawableBanner(R.drawable.banner_3));
        banners.add(new DrawableBanner(R.drawable.banner_4));
        banners.add(new DrawableBanner(R.drawable.banner_5));
        banners.add(new DrawableBanner(R.drawable.banner_6));
        banners.add(new DrawableBanner(R.drawable.banner_7));
        bannerSlider.setBanners(banners);

        spinnerBookType = view.findViewById(R.id.type_spinner);
        Button checkButton = view.findViewById(R.id.search_button);
        listView = view.findViewById(R.id.main_list_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_book_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBookType.setAdapter(adapter);

        bookModelList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        checkButton.setOnClickListener(v -> {
            String bookType = spinnerBookType.getSelectedItem().toString();

            if (bookType.equals("Horror")){
               selectItem = 1;
            }else if (bookType.equals("Romance")){
                selectItem = 2;
            }

            getBookData(selectItem);
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            BookModel Book= bookModelList.get(position);
            Intent intent = new Intent(getContext(),  DetailActivity.class);
            intent.putExtra("book_title", Book.getBookTitle());
            intent.putExtra("book_contents",Book.getBookContents());
            intent.putExtra("book_image", Book.getBookImage());
            startActivity(intent);
        });

        return view;
    }

    private void getBookData(final int select) {
        final ProgressDialog loading = new ProgressDialog(getContext());
        loading.setTitle("Loading");
        loading.setMessage("Check book");
        loading.setCancelable(false);
        loading.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", (dialog, which) -> dialog.dismiss());
        loading.show();


        databaseReference.child("DataBuku")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        bookModelList.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            BookModel bookModel = postSnapshot.getValue(BookModel.class);
                            if (bookModel.getBookType() == select) {
                                bookModelList.add(bookModel);
                            }
                        }
                        loading.hide();
                        BookAdapter adapter = new BookAdapter(getActivity(), bookModelList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Data Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
