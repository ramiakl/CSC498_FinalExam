package com.lau.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    int index;
    String[] courses;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);

        try{

            SQLiteDatabase sql = this.openOrCreateDatabase("laudb", MODE_PRIVATE, null);
            //sql.execSQL("CREATE Table IF NOT EXISTS courses (course_name VARCHAR, link VARCHAR)");

            //sql.execSQL("INSERT INTO courses(course_name, link) VALUES ('Finite Element Method', 'https://www.comsol.com/multiphysics/finite-element-method')");
            //sql.execSQL("INSERT INTO courses(course_name, link) VALUES ('Engineering Entrepreneurship', 'https://byjus.com/commerce/what-is-entrepreneurship/#:~:text=Entrepreneurship%20is%20the%20ability%20and,the%20starting%20of%20new%20businesses.')");
            //sql.execSQL("INSERT INTO courses(course_name, link) VALUES ('Europe and the middle east', 'https://www.britannica.com/')");
            //sql.execSQL("INSERT INTO courses(course_name, link) VALUES ('Mobile Computing', 'https://ionicframework.com/')");

            //sql.execSQL("DROP TABLE courses");

            Cursor c = sql.rawQuery("Select * from courses", null);
            int courseIndex = c.getColumnIndex("course_name");
            c.moveToFirst();
            index = 0;
            courses = new String[4];

            while(index<4){

                courses[index] = c.getString(courseIndex);
                Log.i("cour",courses[index]);
                index++;
                c.moveToNext();
            }

            Log.i("string",Arrays.toString(courses));
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courses);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getApplicationContext(), courses[i], Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),Webview.class);
                    intent.putExtra("chosen",courses[i]);
                    startActivity(intent);
                }
            });

        }catch (Exception e){
            Log.i("exe",e.getMessage());
        }
    }
}