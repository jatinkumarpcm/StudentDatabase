package com.example.jatin.studentdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.attr.borderlessButtonStyle;
import static android.R.attr.id;

public class UpdateStudent extends AppCompatActivity {

    Button update;
    EditText name ,contact,totalfee, feepaid;
    Spinner spinner;
    Dbhelper dbhelper;
    String course[] = {"8th","9th","10th","11th","12th"};
    ArrayAdapter arrayAdapter;
    int update_id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        getSupportActionBar().hide();

        Student  student = (Student) getIntent().getExtras().getSerializable("STUDENT");

        name  = (EditText) findViewById(R.id.et_update_name);
        spinner = (Spinner) findViewById(R.id.update_spinner);
        contact = (EditText) findViewById(R.id.et_update_contact);
        totalfee = (EditText) findViewById(R.id.ettotal_fee_update);
        feepaid = (EditText) findViewById(R.id.etfee_paid_update);
        update = (Button) findViewById(R.id.btnupdate);

        dbhelper = new Dbhelper(this);

        name.setText(student.getName());
        contact.setText(student.getContact());
        totalfee.setText(student.getTotalfee());
        feepaid.setText(student.getFeepaid());


        arrayAdapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,course);
        spinner.setAdapter(arrayAdapter);

        for (int i=0; i<course.length; i++)
        {
            if(course[i].equals(student.getCourse()))
            {
                spinner.setSelection(i);
                break;
            }
        }
         update_id  = student.getId();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String update_name = name.getText().toString();
               String update_spinner = spinner.getSelectedItem().toString();
               String update_contact =  contact.getText().toString();
               String update_totalfee  =  totalfee.getText().toString();
               String update_feepaid = feepaid.getText().toString();

                if(TextUtils.isEmpty(update_name))
                {
                    name.setError("please enter name");
                    return;
                }

                if(TextUtils.isEmpty(update_totalfee))
                {
                    // i have subject as total fee here
                    name.setError("please enter subject");
                    return;
                }
                boolean contact_info = true;
                if(update_contact.length() !=10 )
                {
                    contact.setError("please enter a valid contact");
                    contact_info =false;
                }

                if(TextUtils.isEmpty(update_feepaid))
                {
                    feepaid.setError("please enter fee");
                    return;
                }
                  if(contact_info) {
                      Student student = new Student(update_id, update_name, update_spinner, update_contact, update_totalfee, update_feepaid);
                      int result = dbhelper.updated(student);
                      if (result > 0) {
                          Toast.makeText(getApplicationContext(), "data Updated", Toast.LENGTH_SHORT).show();
                      } else
                          Toast.makeText(getApplicationContext(), "data not Updated", Toast.LENGTH_SHORT).show();
                  }
            }
        });

    }
}