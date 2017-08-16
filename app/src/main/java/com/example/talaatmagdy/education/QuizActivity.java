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


public class QuizActivity extends AppCompatActivity {
    //datamembers for the radiogroups and radiobuttons
    private TextView tbQuestion;
    private Button btnNxt;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4;
    //questions from quiz
    String questions[]= {"Most lines in a C++ program end with a",
                         "main() marks the beginning of a C++ program. What C++ reserved word precedes it?",
                         "What is the correct way to declare an integer variable named \"score\"?",
                         "When you write an illegal C++ statement and try to compile and run the program, you will get a",
                         "Which data type has the largest range?",
                         "About how many decimal places of accuracy does a float have?"};
    //answers to quiz
    String answers[] = {";","int","int score;","compile error","double","4"};
    //options
    String options[] = {":",";",")","}",
                       "#include","using","int","void",
                       "int score","int score;","score:integer","integer score;",
                       "compile error","run-time error","logic error","machine crash",
                       "int","float","double","it depends on the input value",
                        "31","12","6","4"};
    int flag = 0;
    public static int correct=0,incorrect=0;
    int[] randomArray = {0,1,2,3,4,5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
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
        shuffleArray(randomArray);

        tbQuestion.setText(questions[randomArray[flag]]);
        rb1.setText(options[(randomArray[flag]*4)]);
        rb2.setText(options[(randomArray[flag]*4)+1]);
        rb3.setText(options[(randomArray[flag]*4)+2]);
        rb4.setText(options[(randomArray[flag]*4)+3]);


        //Toast.makeText(QuizActivity.this, "", Toast.LENGTH_SHORT).show();
    }
    static void shuffleArray(int[] array) {
        int n = array.length;
        for (int i = 0; i < array.length; i++) {
            // Get a random index of the array past i.
            int random = i + (int) (Math.random() * (n - i));
            // Swap the random element with the present element.
            int randomElement = array[random];
            array[random] = array[i];
            array[i] = randomElement;
        }
    }
    public void nextQuestion(View view)
    {
        //check if no option is checked then throw a toast message to select an option
        if(!(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()))
        {
            Toast.makeText(this,"Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton ans = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
        String ansText = ans.getText().toString();
        //check if the selected answer is right answer or not
            if(ansText.equalsIgnoreCase(answers[randomArray[flag]]))
            {
                correct++; //increment the correct count
            }
            else
            {
                incorrect++; //if does not match decrement the count
            }
        flag++;
        //show only 5 questions
        if(flag < 5)
        {
            rg.clearCheck();//clear the previous radio button
            tbQuestion.setText(questions[randomArray[flag]]);//set the question
            //set the options
            rb1.setText(options[(randomArray[flag]*4)]);
            rb2.setText(options[(randomArray[flag]*4)+1]);
            rb3.setText(options[(randomArray[flag]*4)+2]);
            rb4.setText(options[(randomArray[flag]*4)+3]);
        }
        else
        {
            //create a bundle of correct answers and incorrect answer
            Bundle bundle = new Bundle();
            bundle.putInt("Correct",correct);
            bundle.putInt("Incorrect",incorrect);
            //start an activity to pass result to resultactivity
            Intent in = new Intent(getApplicationContext(),ResultActivity.class);
            in.putExtras(bundle);
            startActivity(in);
        }
    }
}
