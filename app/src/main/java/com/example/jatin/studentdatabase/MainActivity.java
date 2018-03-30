package com.example.jatin.studentdatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{


    EditText name,contact, totalfee, feepaid;
    Spinner spinner;
    Button save, saveall;
    String course[] = {"8th","9th","10th","11th","12th"};
    Dbhelper dbhelper;

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this).setTitle("Exit app").setMessage("Are you sure")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.super.onBackPressed();
                        System.exit(1);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                  AlertDialog alert = builder.create();
                  alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.BLACK);

        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLACK);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.etname);
        contact = (EditText) findViewById(R.id.etcontact);
        totalfee = (EditText) findViewById(R.id.ettotal_fee);
        feepaid = (EditText) findViewById(R.id.etfee_paid);
        spinner = (Spinner) findViewById(R.id.spinner);
        save = (Button) findViewById(R.id.btnsave);
        saveall  = (Button) findViewById(R.id.btnsaveAll);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,course);
        spinner.setAdapter(arrayAdapter);

        getSupportActionBar().hide();


        saveall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StudentList.class);
                startActivity(intent);
            }
        });

       dbhelper = new Dbhelper(this);
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String st_name = name.getText().toString();
               if(TextUtils.isEmpty(st_name)) {name.setError("please provide name");return;}
               String st_course = spinner.getSelectedItem().toString();
               String st_contact = contact.getText().toString();

               String st_toatlfee = totalfee.getText().toString();
               if(TextUtils.isEmpty(st_toatlfee))
               {
                   // here we take totalfee as subject
                   totalfee.setError("please provide subject");
                   return;
               }
               String st_feepaid   =feepaid.getText().toString();
               if(TextUtils.isEmpty(st_feepaid))
               {
                   feepaid.setError("please provide Fee");
                   return;
               }
               Student student   = new Student(st_name,st_course,st_contact,st_toatlfee,st_feepaid);

               boolean contactinfo  = true;

               if(st_contact.length() !=10)
               {
                   // Toast.makeText(MainActivity.this, "p", Toast.LENGTH_SHORT).show();contact.setError("please enter valid Contact");
                   contact.setError("please enter a  valid contact");
                   contactinfo = false;

               }



                 if(contactinfo) {
                     long hasbeeninserted = dbhelper.inserted(student);
                     if (hasbeeninserted != -1) {
                         Toast.makeText(getApplication(), "data Inserted", Toast.LENGTH_SHORT).show();
                     } else
                         Toast.makeText(getApplication(), "data not Inserted", Toast.LENGTH_SHORT).show();
                 }
           }});

    }

}
