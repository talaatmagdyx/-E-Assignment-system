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


public class QuizActivity4 extends AppCompatActivity {
    //data members
    private TextView tbQuestion;
    private Button btnNxt;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4;
    //questions from quiz
    String questions[]= {"The three basic flow-of-control patterns are sequence, ___________ and  ____________.",
            "Suppose x = 0; y = 5; Is the following condition true or false? if (x > 0 && x < 10 || y = = 4)",
            "Which of the following is a legal way to create a C-style symbolic constant?",
            "The three basic loop statements in C++ are while, _______, and _________.",
            "Is Nadal better player than djokovic"};
    //answers to quiz
    String answers[] = {"decision, repetition, or branch and return","false","#define MAX_VAL 30","for, do-while"};
    //options
    String options[] = {"if else","for","while","decision, repetition, or branch and return",
            "true","false","true","false",
            "#define MAX_VAL = 30;","#define MAX_VAL 30","#define MAX_VAL = 30","#define MAX_VAL",
            "for, do-while","if","switch","return"
            };
    int trueorFalse[] = {0,1,0,0};
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
        ////shuffle the array to get random question
        QuizActivity.shuffleArray(randomArray);
        if(trueorFalse[randomArray[flag]] == 0) {
            rb3.setVisibility(View.VISIBLE);
            rb4.setVisibility(View.VISIBLE);
            tbQuestion.setText(questions[randomArray[flag]]);
            rb1.setText(options[(randomArray[flag] * 4)]);
            rb2.setText(options[(randomArray[flag] * 4) + 1]);
            rb3.setText(options[(randomArray[flag] * 4) + 2]);
            rb4.setText(options[(randomArray[flag] * 4) + 3]);
        }
        else
        {
            tbQuestion.setText(questions[randomArray[flag]]);
            rb1.setText(options[(randomArray[flag] * 4)]);
            rb2.setText(options[(randomArray[flag] * 4) + 1]);
            rb3.setVisibility(View.INVISIBLE);
            rb4.setVisibility(View.INVISIBLE);
        }

        //Toast.makeText(QuizActivity.this, "", Toast.LENGTH_SHORT).show();
    }
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
            correct++; //check the selected answer with correct answer
        }
        else
        {
            incorrect++;//increment the incorrect count
    }
        flag++;
        if(flag < 4 )
        {
            if(trueorFalse[randomArray[flag]] == 0) {
                //if the question is not true or false question
                //make the rb3 and rb4 visible
                rb3.setVisibility(View.VISIBLE);
                rb4.setVisibility(View.VISIBLE);
                //clear the previously checked radio button
                rg.clearCheck();
                //set the question and answers
                tbQuestion.setText(questions[randomArray[flag]]);
                rb1.setText(options[(randomArray[flag] * 4)]);
                rb2.setText(options[(randomArray[flag] * 4) + 1]);
                rb3.setText(options[(randomArray[flag] * 4) + 2]);
                rb4.setText(options[(randomArray[flag] * 4) + 3]);
            }
            else{ //if the question is a true or false question
                rg.clearCheck();
                tbQuestion.setText(questions[randomArray[flag]]);
                rb1.setText(options[(randomArray[flag] * 4)]);
                rb2.setText(options[(randomArray[flag] * 4) + 1]);
                //hide the radiobutton rb3 and rb4
                rb3.setVisibility(View.INVISIBLE);
                rb4.setVisibility(View.INVISIBLE);
            }

        }
        else
        {
            //create bundle to get correct and incorrect answers
            Bundle bundle = new Bundle();
            bundle.putInt("Correct",correct);
            bundle.putInt("Incorrect",incorrect);
            //create and start activity
            Intent in = new Intent(getApplicationContext(),ResultActivity.class);
            in.putExtras(bundle);
            startActivity(in);
        }
    }
}
