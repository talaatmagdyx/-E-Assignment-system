package com.example.talaatmagdy.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by talaatmagdy on 5/22/17.
 */


public class course_dash extends AppCompatActivity {
    public static final String Course_name="coursename";
    public static final String Course_id="courseid";

     EditText ename_course;
     EditText enumber_course;
     EditText e_point;
     EditText e_semester;
     EditText e_id;
     Button b_add;
     Button b_dash;
     Button b_progile;


    DatabaseReference databaseCourse;

    ListView listView_course;
    List<Course> courselist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        //DatabaseReference
        databaseCourse= FirebaseDatabase.getInstance().getReference("courses");

        //EditText
        ename_course=(EditText)findViewById(R.id.name_course);
        enumber_course=(EditText)findViewById(R.id.number_course);
        e_point=(EditText)findViewById(R.id.point_course);
        e_semester=(EditText)findViewById(R.id.semester_course);
        e_id=(EditText)findViewById(R.id._id_course) ;

        //listview
        listView_course=(ListView)findViewById(R.id.listview_course);
        //list
        courselist= new ArrayList<>();

        //add Button
        b_add=(Button)findViewById(R.id.add_course);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Add_Course();
            }
        });

        //profile
        b_progile=(Button)findViewById(R.id.profile_Go_);
        b_progile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(course_dash.this,profile.class);
                startActivity(intent_1);
            }
        });

        //Dash
        b_dash=(Button)findViewById(R.id.dash_board_);
        b_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(course_dash.this,profile.class);
                startActivity(intent_1);
            }
        });

        //action listview of course
        listView_course.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = courselist.get(position);

                Intent intent = new Intent(getApplicationContext(),AddAssignment.class);
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
                Course_List adapter = new Course_List(course_dash.this,courselist);
                listView_course.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showCourse() {

    }


    public void Add_Course()
    {
        String nameOFCourse=ename_course.getText().toString().trim();
        String numberOfCourse=enumber_course.getText().toString().trim();
        String pointOfCourse=e_point.getText().toString().trim();
        String semesterOfCourse=e_semester.getText().toString().trim();

        if (!TextUtils.isEmpty(nameOFCourse)&&!TextUtils.isEmpty(numberOfCourse)
                &&!TextUtils.isEmpty(pointOfCourse)&&!TextUtils.isEmpty(semesterOfCourse))
        {
            String id= databaseCourse.push().getKey();

            Course course=new Course(id,nameOFCourse,numberOfCourse,pointOfCourse,semesterOfCourse);

            databaseCourse.child(id).setValue(course);

            Toast.makeText(this,"Course Added...",Toast.LENGTH_SHORT).show();

        }
    }
}