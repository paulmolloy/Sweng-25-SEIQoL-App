package com.example.sweng.seiqol_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Screen7InterviewerRecord extends AppCompatActivity  {
    String area1;
    String area2;
    String area3;
    String area4;
    String area5;
    Intent i;
    Intent j;
    RadioGroup underRGroup, boredomRGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen7_interviewer_record);
        underRGroup = (RadioGroup) findViewById(R.id.understanding_rbg);
        boredomRGroup = (RadioGroup) findViewById(R.id.boredom_rbg);
        underRGroup.check(R.id.understanding_0_rb);
        underRGroup.check(R.id.boredom_0_rb);


        underRGroup.getCheckedRadioButtonId();
        boredomRGroup.getCheckedRadioButtonId();
        //Button next = (Button) findViewById(R.id.Next);

        Button next = (Button) findViewById(R.id.next_7);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                i = new Intent(Screen7InterviewerRecord.this, MainActivity.class);

                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.back_7);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                i = new Intent(Screen7InterviewerRecord.this, MainActivity.class);

                startActivity(j);
            }
        });



    }


}