package com.lau.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try{

            SQLiteDatabase sql = this.openOrCreateDatabase("laudb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS courses (course_name VARCHAR, link VARCHAR)");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}