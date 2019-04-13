package com.efdalincesu.nerede.util;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

public class Snack {

    public static void onFailed(View view,String message){
        Snackbar snackbar=Snackbar.make(view,message,Snackbar.LENGTH_LONG);
        View view1=snackbar.getView();
        view1.setBackgroundColor(Color.RED);
        TextView textView=view1.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.show();
    }

    public static void onSuccess(View view,String message){
        Snackbar snackbar=Snackbar.make(view,message,Snackbar.LENGTH_LONG);
        View view1=snackbar.getView();
        view1.setBackgroundColor(Color.GRAY);
        TextView textView=view1.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.show();
    }
}
