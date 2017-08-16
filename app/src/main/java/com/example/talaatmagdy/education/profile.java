package com.example.talaatmagdy.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by talaatmagdy on 5/22/17.
 */

public class profile extends AppCompatActivity {
    EditText e_id;
    EditText e_username;
    EditText e_email;
    EditText e_password;
    EditText e_number;
    EditText e_gender;

    Button b_dash;
    Button b_update;
    Button b_signout;

    public static String gender;

    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);
        //get firebase auth instance
        //get current user
        // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //EditText
        e_id=(EditText)findViewById(R.id.your_id);
        e_username=(EditText)findViewById(R.id.user_name_student);
        e_email=(EditText)findViewById(R.id.email_student);
        e_password=(EditText)findViewById(R.id.password_student);
        e_number=(EditText)findViewById(R.id.personal_number_student);
        e_gender=(EditText)findViewById(R.id.your_gender);

        //firebasehttps://projectapp-24302.firebaseio.com/Users
        mRef= new Firebase("https://projectapp-24302.firebaseio.com/Users");

        b_update=(Button) findViewById(R.id.update_user);
        b_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // User user = new User(name, email);
             //   mDatabase.child("users").child(userId).setValue(user);



            }

            /*    if (user != null && !e_gender.getText().toString().trim().equals("")) {
                    user.updateEmail(e_gender.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Doctor_Profile.this, "Email address is updated. Please sign in with new email id!", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(Doctor_Profile.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else if (e_gender.getText().toString().trim().equals("")) {
                    e_gender.setError("Enter email");
                }
            }
            */
        });

    //dashboard button
        b_dash=(Button)findViewById(R.id.dash);
        b_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender.equals("Student"))
                {
                    Intent mintent = new Intent(profile.this,dashboard_doctor.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mintent);
                }

                else
                    {

                        Intent mintent = new Intent(profile.this,dashboard_student.class);
                        mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mintent);
                    }

            }
        });
        b_signout=(Button)findViewById(R.id.sign_out_);
        b_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });



}



    @Override
    protected void onStart() {
        super.onStart();
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Users users = dataSnapshot.getValue(Users.class);

                String username = users.getUsername_user();
                e_username.setText(username);

                String email = users.getEmail_user();
                e_email.setText(email);

                String password = users.getPassword_users();
                e_password.setText(password);

                String number = users.getNumber_users();
                e_number.setText(number);

                String id = users.getId_user();
                e_id.setText(id);

                gender = users.getGender_users();
                e_gender.setText(gender);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    //sign out method
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
       /* Intent intent = new Intent(profile.this,login.class);
        startActivity(intent);
        finish();*/
    }






}
