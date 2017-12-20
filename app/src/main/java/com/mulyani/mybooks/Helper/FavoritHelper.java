package com.mulyani.mybooks.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mulyani on 12/19/17.
 */

// menyimpan data yang difav ke db lokal (sqlite)
public class FavoritHelper extends SQLiteOpenHelper{
    SQLiteDatabase myDB;
    private static final String DATABASE_NAME= "favorite.db";
    private static final int DATABASE_VERSION= 1;
    public static final String TABLE_NAME = "FAV_TAB";

    ///utk memberikan nilai awal
    public FavoritHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //utk buat table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE FAV_TAB(id integer primary key, judul_cerpen text null, full_text_cerpen text null, image_cerpen text null)";
        db.execSQL(sql);
    }

    //utk hapus table sqlite
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FAV_TAB");
    }

    //mengambil data dari sqlite
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        return data;

    }

    //utk tambah data
    public boolean addData(ContentValues values){
        myDB = this.getWritableDatabase();

        long result = myDB.insert(TABLE_NAME,null,values);

        if (result == -1 ){
            return false;
        }else {
            return true;
        }
    }

    //utk hapus data
    public Integer deleteData(String judul){
        myDB = this.getWritableDatabase();
        return myDB.delete(TABLE_NAME, "judul_cerpen = ?", new String[] {judul});
    }
}
