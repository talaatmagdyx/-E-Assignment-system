package com.example.talaatmagdy.education;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by talaatmagdy on 5/5/17.
 */

public class login extends AppCompatActivity{
    public static final String Course_name="coursename";

    private EditText el_email;
    private EditText el_password;
    private Button l_signin;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ProgressDialog mprogress;

    private Firebase mRef;

   public static  String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //fireBase
        mAuth=FirebaseAuth.getInstance();

      /*  if (mAuth.getCurrentUser() !=null)
        {
            Intent mintent = new Intent(login.this,dashboard_doctor.class);
            mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mintent);
        }
        */
        mRef= new Firebase("https://projectapp-24302.firebaseio.com/Users");

        //DatabaseReference
        mDatabase= FirebaseDatabase.getInstance().getReference().child("User");


        //progressDialog
        mprogress=new ProgressDialog(this);

        //EditText
        el_email=(EditText)findViewById(R.id.l_email);
        el_password=(EditText)findViewById(R.id.l_password);

        //Button Back
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,Main2.class);
                startActivity(intent);
            }
        });

        // button sign up
        Button signup = (Button)findViewById(R.id.signup_d);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);
            }
        });

        //get Intent
      //  Intent intent=getIntent();

        // gender=intent.getStringExtra(register.gender_);


        //Button sign in
        l_signin=(Button)findViewById(R.id.l_sign_in);
        l_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    //check Login
    private void checkLogin()
    {
        String email = el_email.getText().toString().trim();
        String password = el_password.getText().toString().trim();

        //check if empty
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
        }


        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password))
        {
            mprogress.setMessage("Signing in ..............");
            mprogress.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {


                        //successful sign up
                        Toast.makeText(login.this,"successful Log in ",Toast.LENGTH_SHORT).show();

                        //progress
                        mprogress.dismiss();

                        Intent mintent = new Intent(login.this,profile.class);
                        mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mintent.putExtra("id_1",task.getResult().getUser().getUid());
                        startActivity(mintent);
                        //checkUserExist();

                    }
                    else
                    {
                        Toast.makeText(login.this,"error ....login",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

/*
    public void ret()
    {
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Users users = dataSnapshot.getValue(Users.class);


                 gender = users.getGender_users();

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
    //check user exist in database

  /*  private void checkUserExist() {
        //id of users
        final String user_id=mAuth.getCurrentUser().getUid();
        //
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if(dataSnapshot.hasChild(user_id))
                {
                    Intent mintent = new Intent(login.this,dashboard_doctor.class);
                    mintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mintent);

                }
                else
                {
                    Toast.makeText(login.this,"You need to setup your account",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    */
}
