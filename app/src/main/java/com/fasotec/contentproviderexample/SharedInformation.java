package com.fasotec.contentproviderexample;

import android.provider.BaseColumns;

/**
 * Created by stephaneki on 16/02/2017 .
 *
 */
public class SharedInformation {


    public static class Book implements BaseColumns {
        private Book() {
        }

        public static final String BOOK_TABLE_NAME = "books";

        public static final String BOOK_ID = "BOOK_ID";
        public static final String BOOK_TITLE = "BOOK_TITLE";
        public static final String BOOK_AUTHOR = "BOOK_AUTHOR";


        public static final String BOOK_CREATE_TABLE_QUERY = "CREATE TABLE " + BOOK_TABLE_NAME
                + " (" + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BOOK_TITLE + " VARCHAR(255),"
                + BOOK_AUTHOR + " VARCHAR(255)" + ");";

        public static final String BOOK_DELETE_TABLE_QUERY = "DROP TABLE IF EXISTS " + BOOK_TABLE_NAME;
    }
}
