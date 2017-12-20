package com.mulyani.mybooks.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mulyani.mybooks.Model.ModelBuku;
import com.mulyani.mybooks.R;

import java.util.ArrayList;

/**
 * Created by Mulyani on 12/19/17.
 */

//menampilkan data dari db ke favorit
public class FavoritAdapter extends ArrayAdapter<ModelBuku> {
    private Context context;
    int mResource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */

    public FavoritAdapter(Context context, int resource, ArrayList<ModelBuku> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        String judul = getItem(position).getJudul_cerpen();
        String img = getItem(position).getImage_cerpen();
        String full = getItem(position).getFull_text_cerpen();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource,parent,false);

        ImageView imageCerpen = convertView.findViewById(R.id.image_cerpen);
        TextView judulfav = convertView.findViewById(R.id.judul_fav);
        TextView fulltext = convertView.findViewById(R.id.fulltext);

        judulfav.setText(judul);
        fulltext.setText(full);

        //tampil gambar
        Glide.with(convertView).load(img).into(imageCerpen);

        return convertView;
    }
}
