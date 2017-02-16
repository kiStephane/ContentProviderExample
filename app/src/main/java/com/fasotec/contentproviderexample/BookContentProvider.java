package com.fasotec.contentproviderexample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import static com.fasotec.contentproviderexample.SharedInformation.Book.BOOK_ID;
import static com.fasotec.contentproviderexample.SharedInformation.Book.BOOK_TABLE_NAME;

public class BookContentProvider extends ContentProvider {

    public  static  final  Uri CONTENT_URI = Uri.parse("content://com.fasotec.contentproviderexample");

    private DataBaseHelper dbHelper;

    private static final String CONTENT_PROVIDER_MIME = "vnd.android.cursor.item/com.fasotec.content.provider.books";

    public BookContentProvider() {
    }

    /**
     * handle requests to delete one or more rows
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            if (id < 0) {
                return db.delete(BOOK_TABLE_NAME, selection, selectionArgs);
            } else {
                String[] args = {Long.toString(id)};
                return db.delete(BOOK_TABLE_NAME, BOOK_ID + "= ?", args);
            }
        } finally {
            db.close();
        }
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return CONTENT_PROVIDER_MIME;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            long id = db.insertOrThrow(BOOK_TABLE_NAME, null, values);
            if (id == -1) {
                throw new RuntimeException(String.format("%s : Failed to insert [%s] for unknown reason."
                        , "BookContentProvider", values));
            } else {
                return ContentUris.withAppendedId(uri, id);
            }
        } finally {
            db.close();
        }
    }

    /**
     * Initialize your content provider on startup.
     *
     * @return true if everything went well and false otherwise
     */
    @Override
    public boolean onCreate() {
        dbHelper = new DataBaseHelper(getContext());
        return true;
    }

    /**
     * handle query requests from clients
     */
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (id < 0) {
            return db.query(BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        }else{
            String[] args = {Long.toString(id)};
            return db.query(BOOK_TABLE_NAME, projection, BOOK_ID+" = ?", args, null, null, null, null);
        }
    }

    /**
     * Handle requests to update one or more rows.
     */
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if (id < 0) {
                return db.update(BOOK_TABLE_NAME, values, selection, selectionArgs);
            } else {
                String[] args = {Long.toString(id)};
                return db.update(BOOK_TABLE_NAME, values, SharedInformation.Book.BOOK_ID + " = ?", args);
            }
        } finally {
            db.close();
        }
    }

    /**
     * Extract Id from Uri
     *
     * @param uri Uri object that contains the Id to extract
     * @return the Id in long format or -1 if id not found
     */
    private long getId(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment != null) {
            try {
                return Long.parseLong(lastPathSegment);
            } catch (NumberFormatException e) {
                Log.e("BookContentProvider", "Number Format Exception : " + e);
            }
        }
        return -1;
    }
}
