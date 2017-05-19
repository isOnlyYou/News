package com.example.acer.todaynews.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by acer on 2017/3/22.
 */
public class MyHelper extends SQLiteOpenHelper{

    public MyHelper(Context context) {
        super(context, "phone_num.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table phone_num(pid Integer primary key autoincrement," +
                "tele_num text unique,username text unique,password text,picture text,describe text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
