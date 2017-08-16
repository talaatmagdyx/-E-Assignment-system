package com.example.talaatmagdy.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity2 extends AppCompatActivity {
    //data members
    private TextView tbQuestion;
    private Button btnNxt;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4;
    //questions array
    String questions[]= {"What instruction will display data on the screen from a C++ program?",
            "About how many decimal places of accuracy does a float have?",
            "What is the value of the expression 25 % 3?",
            "Name two libraries that should be #include'd at the top of a C++ program."
    };
    //answers array
    String answers[] = {"cout","4","1","#include<iostream>"};
    //options array
    String options[] ={"int","cin","cout","display","31","12","6","4","8","1","0","Undefined","#include<iostream>","#include<abc>","#include<android>",
            "#include<ios>"};
    int flag = 0;
    public static int correct=0,incorrect=0;
    int[] randomArray = {0,1,2,3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //connect to the screen
        tbQuestion = (TextView)findViewById(R.id.textView);
        btnNxt = (Button)findViewById(R.id.btnNextQuestion);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        rb1 = (RadioButton)findViewById(R.id.radioButton);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        rb3 = (RadioButton)findViewById(R.id.radioButton3);
        rb4 = (RadioButton)findViewById(R.id.radioButton4);
        //int[] randomArray = {0,1,2,3,4};
        correct = 0;
        incorrect = 0;
        //shuffle the array to get random questions
        QuizActivity.shuffleArray(randomArray);
        //set the questions and options
        tbQuestion.setText(questions[randomArray[flag]]);
        rb1.setText(options[(randomArray[flag]*4)]);
        rb2.setText(options[(randomArray[flag]*4)+1]);
        rb3.setText(options[(randomArray[flag]*4)+2]);
        rb4.setText(options[(randomArray[flag]*4)+3]);


        //Toast.makeText(QuizActivity.this, "", Toast.LENGTH_SHORT).show();
    }//onCreate ends
    //button click
    public void nextQuestion(View view)
    {
        //throw a toast message if no option is selected
        if(!(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()))
        {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton ans = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
        //get the selected answer
        String ansText = ans.getText().toString();
        //check the selected answer with correct answer
        if(ansText.equalsIgnoreCase(answers[randomArray[flag]]))
        {
            correct++; //increment the correct count
        }
        else
        {
            incorrect++; //increment the incorrect count
        }
        flag++;
        if(flag < questions.length)
        {
            //clear the previosuly checked radiobutton
            rg.clearCheck();
            //set the question and answers
            tbQuestion.setText(questions[randomArray[flag]]);
            rb1.setText(options[(randomArray[flag]*4)]);
            rb2.setText(options[(randomArray[flag]*4)+1]);
            rb3.setText(options[(randomArray[flag]*4)+2]);
            rb4.setText(options[(randomArray[flag]*4)+3]);
        }
        else
        {
            //create bundle to get correct and incorrect answers
            Bundle bundle = new Bundle();
            bundle.putInt("Correct",correct);
            bundle.putInt("Incorrect",incorrect);
            //start activity
            Intent in = new Intent(getApplicationContext(),ResultActivity.class);
            in.putExtras(bundle);
            startActivity(in);
        }
    }
}