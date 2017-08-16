package com.example.talaatmagdy.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by talaatmagdy on 5/23/17.
 */


public class ViewAssignment extends AppCompatActivity {
    public static final String Course_name="coursename";
    public static final String Course_id="courseid";

    ListView listView_assignment;
    List<Assignment> assignmentslist;

    DatabaseReference databaseAssignment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_assignment);

        //DatabaseReference

        //listview
        listView_assignment=(ListView)findViewById(R.id.listview_viewassignment);
        //list
        assignmentslist= new ArrayList<>();

        //get Intent
        final Intent intent=getIntent();

        String id=intent.getStringExtra(course_dash.Course_id);
        String name=intent.getStringExtra(course_dash.Course_name);

        databaseAssignment= FirebaseDatabase.getInstance().getReference("assignment").child(id);



        listView_assignment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent1 = new Intent(ViewAssignment.this,MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseAssignment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshots) {
                assignmentslist.clear();

                for (DataSnapshot AssignmentSnapshot : dataSnapshots.getChildren())
                {
                    Assignment assignment = AssignmentSnapshot.getValue(Assignment.class);

                    assignmentslist.add(assignment);

                }
                Assignment_List adapter = new Assignment_List(ViewAssignment.this,assignmentslist);
                listView_assignment.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}