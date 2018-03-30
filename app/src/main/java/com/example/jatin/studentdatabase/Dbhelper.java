package com.example.jatin.studentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.R.attr.version;

/**
 * Created by jatin on 1/18/2018.
 */

public class Dbhelper extends SQLiteOpenHelper {

    public static final String database_name = "School.db";
    public static final String database_table = "Student";
    public static final String Student_name = "name";
    public static final String Student_contact = "contact";
    public static final String Student_course = "course";
    public static final String Student_totalfee = "totalfee";
    public static final String Student_feepaid = "feepaid";

    public Dbhelper(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("create table "+database_table+"(id INTEGER PRIMARY KEY AUTOINCREMENT ,name String,course String,contact String,totalfee String,feepaid String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("drop table if exists "+database_table);
       onCreate(db);
    }


    public long inserted(Student student) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Student_name,student.getName());
        content.put(Student_course,student.getCourse());
        content.put(Student_contact,student.getContact());
        content.put(Student_totalfee,student.getTotalfee());
        content.put(Student_feepaid,student.getFeepaid());
        long result = db.insert(database_table,null,content);
        return result;
    }


    public ArrayList getAllContact() {

        ArrayList list = new ArrayList();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+database_table,null);
        cursor.moveToFirst();
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String course  = cursor.getString(2);
            String contact  = cursor.getString(3);
            String totalfee = cursor.getString(4);
            String feepaid =  cursor.getString(5);

            Student student = new Student(id,name, course, contact,totalfee, feepaid);
            list.add(student);
        }
        return  list;
    }


    public int updated(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentvalues  = new ContentValues();
        contentvalues.put(Student_name,student.getName());
        contentvalues.put(Student_course,student.getCourse());
        contentvalues.put(Student_contact,student.getContact());
        contentvalues.put(Student_totalfee,student.getTotalfee());
        contentvalues.put(Student_feepaid,student.getFeepaid());

         return db.update(database_table,contentvalues,"id=?", new String[]{String.valueOf(student.getId())});
    }

    public int delete(Student student)
    {
        SQLiteDatabase db = getWritableDatabase();

        
        return db.delete(database_table,"id=?",new String[]{String.valueOf(student.getId())});


    }
}
