package com.jianjiaoproduction.youremoticons.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The database helper class which handles storing and retrieving the
 * user-defined emoticons, and its usage frequency
 * Created by zhibzhang on 6/1/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "emoticon.db";

    //Table CUST_EMOTICONS
    private static final String TBL_USER_EMOTICONS = "CUST_EMOTICONS";
    private static final String COL_KEY_ID = "KEY_ID";
    private static final String COL_KEY_CONTENT = "KEY_CONTENT";
    private static final String COL_FREQUENCY = "FREQUENCY";

    private static final String CREATE_USER_EMOTICONS_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TBL_USER_EMOTICONS + " (" + COL_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_KEY_CONTENT + " TEXT, " + COL_FREQUENCY + " INTEGER);";

    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_EMOTICONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
