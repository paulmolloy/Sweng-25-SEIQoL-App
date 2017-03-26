package com.example.sweng.seiqol_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Screen7InterviewerRecord extends AppCompatActivity  {
    long time;
    ArrayList<String> data = new ArrayList<>();
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
        Calendar calendar = Calendar.getInstance();
        time = calendar.getTimeInMillis();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
        }
        long startTime = Long.parseLong(data.get(2));
        long totalTime = (time-startTime)/1000;
        TextView tv = (TextView) findViewById(R.id.time_taken_tv);
        tv.setText(Long.toString(totalTime));
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