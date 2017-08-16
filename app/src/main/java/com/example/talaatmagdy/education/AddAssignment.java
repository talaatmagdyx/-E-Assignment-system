package com.example.talaatmagdy.education;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAssignment extends AppCompatActivity {

     EditText e_name_assignment;
     EditText e_decription;
     EditText e_movtiation;
     EditText e_deadline;
     TextView textview_appear;
     Spinner spinner_type;

    Button add_assignment;
    Button Dash;
    Button your_Porfile;

    ListView listView_assignment;

    DatabaseReference databaseAssignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        //EditText
        e_name_assignment = (EditText)findViewById(R.id.name_assignment);
        e_decription = (EditText)findViewById(R.id.decription);
        e_movtiation = (EditText)findViewById(R.id.Motivation);
        e_deadline = (EditText)findViewById(R.id.deadline);

        //textview
        textview_appear=(TextView)findViewById(R.id.textview_appear);
        //spinner
        spinner_type=(Spinner)findViewById(R.id.spinner_type);
        //listview
        listView_assignment=(ListView)findViewById(R.id.listview_assignment);

        //get Intent
        Intent intent=getIntent();

        String id=intent.getStringExtra(course_dash.Course_id);
        String name=intent.getStringExtra(course_dash.Course_name);

        textview_appear.setText(name);

        //DataBase
        databaseAssignment= FirebaseDatabase.getInstance().getReference("assignment").child(id);

        //DashBoard Button
        Dash=(Button)findViewById(R.id.dash_board);
        Dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(AddAssignment.this,profile.class);
                startActivity(intent_1);
            }
        });

        //profile Button
        your_Porfile=(Button)findViewById(R.id.profile_Go);
        your_Porfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(AddAssignment.this,profile.class);
                startActivity(intent_1);
            }
        });
        //add assignment
        add_assignment=(Button)findViewById(R.id.add_d);
        add_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addassignment();
            }
        });

    }

    private void addassignment() {
        String nameOFassignment=e_name_assignment.getText().toString().trim();
        String decriptionOfassignment=e_decription.getText().toString().trim();
        String movtiaitionOfassignment=e_movtiation.getText().toString().trim();
        String typeOfassignment = spinner_type.getSelectedItem().toString();
        String deadlineOfassignment=e_deadline.getText().toString().trim();

        if (!TextUtils.isEmpty(nameOFassignment)&&!TextUtils.isEmpty(decriptionOfassignment)
                &&!TextUtils.isEmpty(movtiaitionOfassignment)&&!TextUtils.isEmpty(typeOfassignment)
                &&!TextUtils.isEmpty(deadlineOfassignment))
        {
            String id = databaseAssignment.push().getKey();
            Assignment assignment = new Assignment(id,nameOFassignment,decriptionOfassignment,movtiaitionOfassignment,typeOfassignment,deadlineOfassignment);

            databaseAssignment.child(id).setValue(assignment);
            Toast.makeText(this,"Assignment saved successful ..",Toast.LENGTH_SHORT).show();
        }


    }
}
