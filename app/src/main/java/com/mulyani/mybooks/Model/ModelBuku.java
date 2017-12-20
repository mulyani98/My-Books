package com.mulyani.mybooks.Model;

import android.os.Parcel;
import android.os.Parcelable;

//data yg akan disimpan di database firebase
public class ModelBuku implements Parcelable {
    private String id_cerpen;
    private String judul_cerpen;
    private String pengarang_cerpen;
    private String tahun_cerpen;
    private int jenis_cerpen;      //jenis
    private String overview_cerpen; //sinopsis
    private String full_text_cerpen;    // full
    private String image_cerpen;
    private String harga_cerpen;
    private String jumlah_kata;

    public ModelBuku(String id_cerpen, String judul_cerpen, String pengarang_cerpen,
                     String tahun_cerpen, int jenis_cerpen, String overview_cerpen,
                     String full_text_cerpen, String image_cerpen, String harga_cerpen, String jumlah_kata) {
        this.id_cerpen = id_cerpen;
        this.judul_cerpen = judul_cerpen;
        this.pengarang_cerpen = pengarang_cerpen;
        this.tahun_cerpen = tahun_cerpen;
        this.jenis_cerpen = jenis_cerpen;
        this.overview_cerpen = overview_cerpen;
        this.full_text_cerpen = full_text_cerpen;
        this.image_cerpen = image_cerpen;
        this.harga_cerpen = harga_cerpen;
        this.jumlah_kata = jumlah_kata;
    }

    public ModelBuku() {
    }

    public String getId_cerpen() {
        return id_cerpen;
    }

    public void setId_cerpen(String id_cerpen) {
        this.id_cerpen = id_cerpen;
    }

    public String getJudul_cerpen() {
        return judul_cerpen;
    }

    public void setJudul_cerpen(String judul_cerpen) {
        this.judul_cerpen = judul_cerpen;
    }

    public String getPengarang_cerpen() {
        return pengarang_cerpen;
    }

    public void setPengarang_cerpen(String pengarang_cerpen) {
        this.pengarang_cerpen = pengarang_cerpen;
    }

    public String getTahun_cerpen() {
        return tahun_cerpen;
    }

    public void setTahun_cerpen(String tahun_cerpen) {
        this.tahun_cerpen = tahun_cerpen;
    }

    public int getJenis_cerpen() {
        return jenis_cerpen;
    }

    public void setJenis_cerpen(int jenis_cerpen) {
        this.jenis_cerpen = jenis_cerpen;
    }

    public String getOverview_cerpen() {
        return overview_cerpen;
    }

    public void setOverview_cerpen(String overview_cerpen) {
        this.overview_cerpen = overview_cerpen;
    }

    public String getFull_text_cerpen() {
        return full_text_cerpen;
    }

    public void setFull_text_cerpen(String full_text_cerpen) {
        this.full_text_cerpen = full_text_cerpen;
    }

    public String getImage_cerpen() {
        return image_cerpen;
    }

    public void setImage_cerpen(String image_cerpen) {
        this.image_cerpen = image_cerpen;
    }

    public String getHarga_cerpen() {
        return harga_cerpen;
    }

    public void setHarga_cerpen(String harga_cerpen) {
        this.harga_cerpen = harga_cerpen;
    }

    public String getJumlah_kata() {
        return jumlah_kata;
    }

    public void setJumlah_kata(String jumlah_kata) {
        this.jumlah_kata = jumlah_kata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_cerpen);
        dest.writeString(this.judul_cerpen);
        dest.writeString(this.pengarang_cerpen);
        dest.writeString(this.tahun_cerpen);
        dest.writeInt(this.jenis_cerpen);
        dest.writeString(this.overview_cerpen);
        dest.writeString(this.full_text_cerpen);
        dest.writeString(this.image_cerpen);
        dest.writeString(this.harga_cerpen);
        dest.writeString(this.jumlah_kata);
    }

    protected ModelBuku(Parcel in) {
        this.id_cerpen = in.readString();
        this.judul_cerpen = in.readString();
        this.pengarang_cerpen = in.readString();
        this.tahun_cerpen = in.readString();
        this.jenis_cerpen = in.readInt();
        this.overview_cerpen = in.readString();
        this.full_text_cerpen = in.readString();
        this.image_cerpen = in.readString();
        this.harga_cerpen = in.readString();
        this.jumlah_kata = in.readString();
    }

    public static final Creator<ModelBuku> CREATOR = new Creator<ModelBuku>() {
        @Override
        public ModelBuku createFromParcel(Parcel source) {
            return new ModelBuku(source);
        }

        @Override
        public ModelBuku[] newArray(int size) {
            return new ModelBuku[size];
        }
    };
}
