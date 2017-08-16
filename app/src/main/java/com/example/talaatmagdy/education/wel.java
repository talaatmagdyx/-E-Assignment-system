package com.example.talaatmagdy.education;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

/**
 * Created by talaatmagdy on 5/5/17.
 */

public class wel  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wel);

        final EditText showHidePasswordEditText = (EditText) findViewById(R.id.ra);
        final ShowHidePasswordEditText showHidePasswordEditText1 = (ShowHidePasswordEditText) findViewById(R.id.hide_show_edittext_password);
        final ShowHidePasswordEditText showHidePasswordEditText3 = (ShowHidePasswordEditText) findViewById(R.id.hide_show_android_edittext_password);


        showHidePasswordEditText.setEnabled(false);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHidePasswordEditText.setEnabled(true);
            }
        });

    }

}
