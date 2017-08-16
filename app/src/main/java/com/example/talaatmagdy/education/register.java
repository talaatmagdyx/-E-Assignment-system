package com.example.talaatmagdy.education;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by talaatmagdy on 5/7/17.
 */

public class register extends AppCompatActivity {

    //public static final String gender_="gender";


    EditText e_password;
    EditText e_username ;
    EditText e_email;
    EditText e_number;
    Spinner sp_gender;


    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    private ProgressDialog mprogress;
     @Override
        protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_register);

        //firebase Auth
         mAuth = FirebaseAuth.getInstance();
         //DatabaseReference
         mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");

         //progressDialog
         mprogress=new ProgressDialog(this);

         // EditText
         e_password = (EditText) findViewById(R.id.password);
         e_username = (EditText) findViewById(R.id.username);
         e_email = (EditText) findViewById(R.id.email);
         e_number = (EditText)findViewById(R.id.numberphone);

         //spinner
         sp_gender=(Spinner)findViewById(R.id.spinner_gen);

         //sign button
         Button sign = (Button) findViewById(R.id.sign_d);
         sign.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(register.this, login.class);
                 startActivity(intent);
             }
         });

         //singup Button
         Button signup = (Button) findViewById(R.id.signup_d);
         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 startRegister();
             }
         });

     }
     public void startRegister()
     {
         final String username=e_username.getText().toString().trim();
         final String email=e_email.getText().toString().trim();
         final String password=e_password.getText().toString().trim();
         final String p_number = e_number.getText().toString().trim();
         final String gender = sp_gender.getSelectedItem().toString();

         //check if empty
         if(TextUtils.isEmpty(username))
         {
             Toast.makeText(this,"Please enter username",Toast.LENGTH_SHORT).show();
         }
         if(TextUtils.isEmpty(email))
         {
             Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
         }
         if(TextUtils.isEmpty(password))
         {
             Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
         }
         if(TextUtils.isEmpty(p_number))
         {
             Toast.makeText(this,"Please enter p_number",Toast.LENGTH_SHORT).show();
         }

         if(TextUtils.isEmpty(gender))
         {
             Toast.makeText(this,"Please enter gender",Toast.LENGTH_SHORT).show();
         }

         if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(email)
                 &&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(p_number)&&!TextUtils.isEmpty(gender))
         {
             mprogress.setMessage("Signing up ..............");
             mprogress.show();

             mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful())
                     {
                        //id of users
                       /*  String user_id=mAuth.getCurrentUser().getUid();
                         //connect database with id users
                         DatabaseReference current_user_db=mDatabase.child(user_id);
                         //add value to data
                         current_user_db.child("username").setValue(username);

                         current_user_db.child("username").setValue(username);
                         current_user_db.child("email").setValue(email);
                         current_user_db.child("password").setValue(password);
                         current_user_db.child("Pnumber").setValue(p_number);
                         current_user_db.child("Gender").setValue(gender);

                        */
                         String id= mDatabase.push().getKey();

                         Users users=new Users(id,username,email,password,p_number,gender);

                         mDatabase.child(id).setValue(users);


                         //successful sign up
                         Toast.makeText(register.this,"successful Registration",Toast.LENGTH_SHORT).show();

                         //progress
                         mprogress.dismiss();

                         //move to log in activity

                         Intent intent = new Intent(getApplicationContext(),login.class);
                         intent.putExtra(gender,users.getGender_users());
                         startActivity(intent);

                     }

                 }
             });
         }
     }
}

