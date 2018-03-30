package com.example.jatin.studentdatabase;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentList extends AppCompatActivity {

    ListView listView;
    Dbhelper dbhelper;
    ArrayAdapter<Student> arrayAdapter;
    ArrayList<Student> list;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        getSupportActionBar().hide();

        listView = (ListView) findViewById(R.id.listview);
        dbhelper  =new Dbhelper(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                      Student student =  list.get(position);

                Intent intent = new Intent(getApplicationContext(),UpdateStudent.class);
                intent.putExtra("STUDENT",  student);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder  builder = new AlertDialog.Builder(StudentList.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure to delete student data?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Student student =   list.get(position);
                        int delstudent  =    dbhelper.delete(student);

                        if(delstudent >0)
                        {
                            Toast.makeText(StudentList.this, "Student deleted", Toast.LENGTH_SHORT).show();
                            list.remove(position);
                            arrayAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(StudentList.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No",null);
                AlertDialog alert = builder.create();
                alert.show();
                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(Color.BLACK);

               Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pbutton.setTextColor(Color.BLACK);
                return true;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        list = dbhelper.getAllContact();
        arrayAdapter  =new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,list);

        listView.setAdapter(arrayAdapter);
    }


}