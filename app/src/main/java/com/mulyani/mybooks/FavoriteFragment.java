package com.mulyani.mybooks;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.mulyani.mybooks.Adapter.FavoriteAdapter;
import com.mulyani.mybooks.Helper.FavoriteHelper;
import com.mulyani.mybooks.Model.BookModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

/* displays saved/favorite stories locally */
public class FavoriteFragment extends Fragment {

    ListView listView;
    ArrayList<BookModel> bookModelArrayList;
    FavoriteHelper favoriteHelper;


    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        listView = view.findViewById(R.id.favorite_list_view);
        favoriteHelper = new FavoriteHelper(this.getContext());

        viewList();

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            BookModel Book= bookModelArrayList.get(position);
            Intent intent = new Intent(getContext(),  DetailFavoriteActivity.class);
            intent.putExtra("book_title", Book.getBookTitle());
            intent.putExtra("book_contents",Book.getBookContents());
            intent.putExtra("book_image", Book.getBookImage());
            startActivity(intent);
        });

        return view;
    }

    private void viewList() {
        Cursor data = favoriteHelper.getData();
        bookModelArrayList = new ArrayList<>();

        while (data.moveToNext()) {
            String bookTitle = data.getString(1);
            String bookContents = data.getString(2);
            String bookImage = data.getString(3);

            BookModel model = new BookModel(null, bookTitle, null, null, 0,
                    null, bookContents, bookImage, null, null);
            Log.d("dataSQLITE", model.getBookTitle());
            bookModelArrayList.add(model);
        }
        data.close();

        FavoriteAdapter adapter = new FavoriteAdapter(getContext(), R.layout.item_fav, bookModelArrayList);
        listView.setAdapter(adapter);
    }
}
