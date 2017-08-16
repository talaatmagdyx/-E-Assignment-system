package com.example.talaatmagdy.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {
    //data members
    TextView tbAnswer,tbcorrect,tbincorrect;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //connect the screen
        tbAnswer = (TextView)findViewById(R.id.tbAnswers);
        btnRestart = (Button)findViewById(R.id.btnRestart);
        tbcorrect = (TextView)findViewById(R.id.tbCorrect);
        tbincorrect = (TextView)findViewById(R.id.tbIncorrect);
        //get the data from intent
        Bundle bundle = getIntent().getExtras();
        //get correct count
        Integer correct1 = bundle.getInt("Correct");
        //get the score
        Integer marks = correct1 * 2;
        //get incorrect count
        Integer incorrect1 = bundle.getInt("Incorrect");
        String correct2 = correct1.toString();
        String marks2 = marks.toString();
        String incorrect2 = incorrect1.toString();
        //set the result to textview
        tbcorrect.setText(correct2);
        tbincorrect.setText(incorrect2);
        tbAnswer.setText(marks2);

        Database database = new Database(this);
        database.insertAssignment(marks2);
    }



    //button to restart the quiz
    public void restartQuiz(View view)
    {
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
    }
}


