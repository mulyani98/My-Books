package com.mulyani.mybooks;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mulyani.mybooks.Adapter.FavoritAdapter;
import com.mulyani.mybooks.Helper.FavoritHelper;
import com.mulyani.mybooks.Model.ModelBuku;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

//menampilkan cerita yg disimpan/favorit scr lokal
public class FavoritFragment extends Fragment {


    ListView listView;
    ArrayList<ModelBuku> listCerpen;
    FavoritHelper dbHelper;


    public static FavoritFragment newInstance() {
        FavoritFragment fragment = new FavoritFragment();
        return fragment;
    }

    public FavoritFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorit, container, false);


        listView = view.findViewById(R.id.favorit_list_view);
        dbHelper = new FavoritHelper(this.getContext());

        viewList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelBuku Book= listCerpen.get(position);
                Intent intent = new Intent(getContext(),  DetailFavoritActivity.class);
                intent.putExtra("judul_cerpen", Book.getJudul_cerpen());
                intent.putExtra("full_text_cerpen",Book.getFull_text_cerpen());
                intent.putExtra("image_cerpen", Book.getImage_cerpen());
                startActivity(intent);
            }
        });
        return view;
    }

    //tampil data list di fav
    private void viewList() {

        Cursor data = dbHelper.getData();
        listCerpen = new ArrayList<>();

        while (data.moveToNext()) {
            String judul_cerpen = data.getString(1);
            String full_text_cerpen = data.getString(2);
            String image_cerpen = data.getString(3);

            ModelBuku model = new ModelBuku(null, judul_cerpen, null, null, 0,
                    null, full_text_cerpen, image_cerpen, null, null);
            Log.d("dataSQLITE", model.getJudul_cerpen());
            listCerpen.add(model);
        }
        data.close();
        FavoritAdapter adapter = new FavoritAdapter(getContext(), R.layout.item_favorit, listCerpen);
        listView.setAdapter(adapter);
    }


}
