package com.fasotec.contentproviderexample;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static com.fasotec.contentproviderexample.BookContentProvider.CONTENT_URI;
import static com.fasotec.contentproviderexample.SharedInformation.Book.BOOK_AUTHOR;
import static com.fasotec.contentproviderexample.SharedInformation.Book.BOOK_ID;
import static com.fasotec.contentproviderexample.SharedInformation.Book.BOOK_TITLE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addBook(View view) {
        ContentValues contact = new ContentValues();
        contact.put(BOOK_TITLE, "Android Content provider");
        contact.put(BOOK_AUTHOR, "John Doe");
        Uri result = getContentResolver().insert(CONTENT_URI, contact);

        if (result != null)
            Toast.makeText(this, String.format("Livre %s ajouté", result.getLastPathSegment()), Toast.LENGTH_LONG).show();
    }

    public void showBooks(View view) {
        String columns[] = new String[]{
                BOOK_ID, BOOK_AUTHOR, BOOK_TITLE
        };
        String details = "";

        Uri mBooks = CONTENT_URI;
        Cursor cursor = getContentResolver().query(mBooks, columns, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    details += cursor.getString(cursor.getColumnIndex(BOOK_ID))
                            + " " + cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR))
                            + " " + cursor.getString(cursor.getColumnIndex(BOOK_TITLE)) + "\n";
                } while (cursor.moveToNext());

                cursor.close();
            }
        }

        DialogFragment newFragment = BooksDetailsDialogFragment.newInstance(details);
        newFragment.show(getFragmentManager(), "dialog");
    }
}
