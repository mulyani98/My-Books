package com.mulyani.mybooks.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mulyani on 12/19/17.
 */

/* save fav data to local db (sqlite) */
public class FavoriteHelper extends SQLiteOpenHelper{
    SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME= "favorite.db";
    private static final int DATABASE_VERSION= 1;
    public static final String TABLE_NAME = "FAV_TAB";

    /* initial values */
    public FavoriteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /* create table */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE FAV_TAB(id integer primary key, book_title text, book_contents text, book_image text)";
        db.execSQL(sql);
    }

    /* delete sql table */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS FAV_TAB");
    }

    /* get data from sqlite */
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

    }

    /* add data */
    public boolean addData(ContentValues values){
        sqLiteDatabase = this.getWritableDatabase();

        long result = sqLiteDatabase.insert(TABLE_NAME,null,values);

        return result != -1;
    }

    /* delete data */
    public Integer deleteData(String judul){
        sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "book_title = ?", new String[] {judul});
    }
}
