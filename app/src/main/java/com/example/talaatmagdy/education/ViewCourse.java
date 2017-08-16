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

public class ViewCourse extends AppCompatActivity {
    public static final String Course_name="coursename";
    public static final String Course_id="courseid";

    ListView listView_course;
    List<Course> courselist;

    DatabaseReference databaseCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_course);

        //DatabaseReference
        databaseCourse= FirebaseDatabase.getInstance().getReference("courses");

        //listview
        listView_course=(ListView)findViewById(R.id.listview_viewcourse);
        //list
        courselist= new ArrayList<>();

        //action listview of course
        listView_course.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = courselist.get(position);

                Intent intent = new Intent(getApplicationContext(),ViewAssignment.class);
                intent.putExtra(Course_id,course.getCourse_id());
                intent.putExtra(Course_name,course.getCourse_name());
                startActivity(intent);

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseCourse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot CourseSnapshot : dataSnapshot.getChildren())
                {
                    Course course = CourseSnapshot.getValue(Course.class);

                    courselist.add(course);

                }
                Course_List adapter = new Course_List(ViewCourse.this,courselist);
                listView_course.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
