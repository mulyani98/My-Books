package com.mulyani.mybooks.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.mulyani.mybooks.Model.ModelBuku;
import com.mulyani.mybooks.R;

import java.util.List;

//menampilkan data yg diambil dari db ke list yg ada di home fragment
public class BukuAdapter extends ArrayAdapter<ModelBuku> {
    public Activity context;
    List<ModelBuku> listCerpen;



    public BukuAdapter(Activity context, List<ModelBuku> listCerpen) {
        super(context, R.layout.list_cerpen, listCerpen);
        this.context = context;
        this.listCerpen = listCerpen;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
//        String judul = getItem(position).getJudul_cerpen();
//        String jenis = String.valueOf(getItem(position).getJenis_cerpen());
//        String harga = String.valueOf(getItem(position).getHarga_cerpen());
//        String tahun = String.valueOf(getItem(position).getTahun_cerpen());
//        String sinops = getItem(position).getOverview_cerpen();
//        String gambar = getItem(position).getImage_cerpen();


        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_cerpen, null, true);

        ImageView coverCerpen = listViewItem.findViewById(R.id.cover_cerpen);
        TextView tvJnsCerpen = listViewItem.findViewById(R.id.tvJnsCerpen);
        TextView tvTahun = listViewItem.findViewById(R.id.tvTahun);
        TextView tvSinopsis = listViewItem.findViewById(R.id.tvSinopsis);
        TextView judulCerpen = listViewItem.findViewById(R.id.judul_cerpen);
        TextView jenisCerpen = listViewItem.findViewById(R.id.jenis_buku);
        TextView thnTerbit = listViewItem.findViewById(R.id.thnTerbit);
        TextView sinopsis = listViewItem.findViewById(R.id.sinopsis);

        TextView hargaCerpen = listViewItem.findViewById(R.id.harga_cerpen);

        ModelBuku cerpen = listCerpen.get(position);
        Glide.with(listViewItem).load(cerpen.getImage_cerpen()).into(coverCerpen);
        judulCerpen.setText(cerpen.getJudul_cerpen());
        jenisCerpen.setText(String.valueOf(cerpen.getJenis_cerpen()));
        thnTerbit.setText(String.valueOf(cerpen.getTahun_cerpen()));
        sinopsis.setText(cerpen.getOverview_cerpen());
        hargaCerpen.setText(String.valueOf(cerpen.getHarga_cerpen()));

        return listViewItem;
    }
}
