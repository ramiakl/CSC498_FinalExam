package com.lau.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    String[] courses;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try{

            SQLiteDatabase sql = this.openOrCreateDatabase("laudb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS courses (course_name VARCHAR, link VARCHAR)");

            sql.execSQL("INSERT INTO courses(course_name, link) VALUES ('Finite Element Method', 'https://www.comsol.com/multiphysics/finite-element-method')");
            sql.execSQL("INSERT INTO courses(course_name, link) VALUES ('Engineering Entrepreneurship', 'https://byjus.com/commerce/what-is-entrepreneurship/#:~:text=Entrepreneurship%20is%20the%20ability%20and,the%20starting%20of%20new%20businesses.')");
            sql.execSQL("INSERT INTO courses(course_name, link) VALUES ('Europe and the middle east', 'https://www.britannica.com/')");
            sql.execSQL("INSERT INTO courses(course_name, link) VALUES ('Mobile Computing', 'https://ionicframework.com/')");

            Cursor c = sql.rawQuery("Select * from courses", null);
            int courseIndex = c.getColumnIndex("course_name");
            c.moveToFirst();

            index = 0;
            courses = new String[4];
            while(c!=null){
                
                courses[index] = c.getString(courseIndex);
                c.moveToNext();
                index++;
            }




        }catch (Exception e){
            e.printStackTrace();
        }
    }
}