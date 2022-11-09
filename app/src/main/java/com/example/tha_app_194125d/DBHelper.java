package com.example.tha_app_194125d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "THA_DB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table item_infor(name TEXT primary key, description TEXT, price TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists item_infor");

    }

    public boolean inserttha_db(String name, String description,String price){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name );
        contentValues.put("description", description );
        contentValues.put("price", price );
        long resault = DB.insert("item_infor", null, contentValues);
        if(resault == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updatetha_db(String name, String description,String price){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name );
        contentValues.put("description", description );
        contentValues.put("price", price );
        Cursor cursor = DB.rawQuery("Select * from THA_DB where name = ?",new String[]{name});
        if(cursor.getCount()>0) {
            long resault = DB.update("item_infor", contentValues, "name=?", new String[]{name});
            if (resault == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public boolean deletetha_db(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from item_infor where name = ?",new String[]{name});
        if(cursor.getCount()>0) {
            long resault = DB.delete("item_infor", "name=?", new String[]{name});
            if (resault == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Cursor gettha_db(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from item_infor",null);

        return cursor;
    }
}
