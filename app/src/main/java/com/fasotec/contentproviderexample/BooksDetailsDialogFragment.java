package com.fasotec.contentproviderexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by stephaneki on 16/02/2017 .
 *
 */
public class BooksDetailsDialogFragment extends DialogFragment {

    public static final String BUNDLE_KEY= "details";

    public static BooksDetailsDialogFragment newInstance(String details){
        BooksDetailsDialogFragment fragment = new BooksDetailsDialogFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY, details);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        String details = getArguments().getString(BUNDLE_KEY);
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(details)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Nothing to do
                    }
                }).create();
    }
}
