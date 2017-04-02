package com.example.sweng.seiqol_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
        }

        Calendar calendar = Calendar.getInstance();
        time = calendar.getTimeInMillis();
        long startTime = Long.parseLong(data.get(2));
        long totalTime = (time-startTime)/1000;
        long mins = totalTime/60;
        long seconds = totalTime % 60;
        String timeString = Long.toString(mins) + " mins " + Long.toString(seconds) + " seconds";

        for(int i=0;i<data.size()-1;i++) Log.e("record", data.get(i));
        TextView tv = (TextView) findViewById(R.id.time_taken_tv);
        tv.setText(timeString);
        underRGroup.getCheckedRadioButtonId();
        boredomRGroup.getCheckedRadioButtonId();
        //Button next = (Button) findViewById(R.id.Next);

        Button next = (Button) findViewById(R.id.next_7);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.set(23, Integer.toString(underRGroup.getCheckedRadioButtonId()));
                data.set(24, Integer.toString(boredomRGroup.getCheckedRadioButtonId()));

                i = new Intent(Screen7InterviewerRecord.this, Screen8Result.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.back_7);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                j = new Intent(Screen7InterviewerRecord.this, PieTesting.class);
                j.putExtra("DATA", data);
                startActivity(j);
            }
        });



    }


}