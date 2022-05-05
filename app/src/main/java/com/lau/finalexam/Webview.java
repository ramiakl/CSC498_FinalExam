package com.lau.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {
// This class displays the webview of the chosen exam

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView view = (WebView) findViewById(R.id.webview);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new WebViewClient());

        Intent x = getIntent();
        String course = x.getStringExtra("chosen");

        //Selecting the link of the chosen course
        SQLiteDatabase sql = this.openOrCreateDatabase("laudb", MODE_PRIVATE, null);
        Cursor c = sql.rawQuery("Select link from courses WHERE course_name ='"+course+"'" , null);

        int courseIndex = c.getColumnIndex("link");
        c.moveToFirst();
        String url = c.getString(courseIndex);
        view.loadUrl(url); // loading the url that we got from the database
    }
}