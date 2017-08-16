package com.example.talaatmagdy.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by talaatmagdy on 5/5/17.
 */


public class Main2  extends AppCompatActivity{
    Button doctor_btn;
    Button student_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        doctor_btn = (Button)findViewById(R.id.doctor_btn);
        student_btn=(Button)findViewById(R.id.student_btn);

        doctor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2.this,register.class);
                startActivity(intent);
                finish();
            }
        });
        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2.this,register.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
