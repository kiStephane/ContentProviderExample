package com.fasotec.contentproviderexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stephaneki on 16/02/2017 .
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    // Nom de la base de données
    public static final String CONTENT_PROVIDER_DB_NAME = "contentproviderexample.db";

    //Version de notre base de données
    public static final int CONTENT_PROVIDER_DB_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, CONTENT_PROVIDER_DB_NAME, null, CONTENT_PROVIDER_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SharedInformation.Book.BOOK_CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SharedInformation.Book.BOOK_DELETE_TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }
}
