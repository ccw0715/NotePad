package com.qianfeng.notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 蔡灿武 on 2016/9/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String name = "note.db";
    private static final int version = 1;
    public static final String userTable = "user";
    public static final String noteTable ="note";

    public DBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + userTable + " (username text,password text);");
        db.execSQL("create table if not exists " + noteTable + " (username text,time text,title text,content text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}