package com.example.sweng.seiqol_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Screen7InterviewerRecord extends AppCompatActivity  {
    long time;
    ArrayList<String> data = new ArrayList<>();
    String[] temp;
    Intent i;
    Intent j;
    RadioGroup underRGroup, boredomRGroup;
    RadioGroup rg1,rg2,rg3,rg4;
    private static final int LIST_INDEX_START = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        temp = new String[4]; //num fields


        setContentView(R.layout.activity_screen7_interviewer_record);
//        underRGroup = (RadioGroup) findViewById(R.id.understanding_rbg);
//        boredomRGroup = (RadioGroup) findViewById(R.id.boredom_rbg);
//        underRGroup.check(R.id.understanding_0_rb);
//        underRGroup.check(R.id.boredom_0_rb);

        rg1 = (RadioGroup) findViewById(R.id.rGroup);
        rg2 = (RadioGroup) findViewById(R.id.rGroup2);
        rg3 = (RadioGroup) findViewById(R.id.rGroup3);
        rg4 = (RadioGroup) findViewById(R.id.rGroup4);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
        }

        Calendar calendar = Calendar.getInstance();
        time = calendar.getTimeInMillis();
        long startTime = Long.parseLong(data.get(2));
        long totalTime = (time - startTime) / 1000;
        long mins = totalTime / 60;
        long seconds = totalTime % 60;
        String timeString = Long.toString(mins) + " mins " + Long.toString(seconds) + " seconds";
        data.set(2, timeString);

        for (int i = 0; i < data.size() - 1; i++) Log.e("record", data.get(i));
        TextView tv = (TextView) findViewById(R.id.timeV);
        tv.setText(timeString);

        TextView weight1 = (TextView) findViewById(R.id.WeightOne);
        weight1.setText(data.get(18));
        TextView weight2 = (TextView) findViewById(R.id.WeightTwo);
        weight2.setText(data.get(19));
        TextView weight3 = (TextView) findViewById(R.id.WeightThree);
        weight3.setText(data.get(20));
        TextView weight4 = (TextView) findViewById(R.id.WeightFour);
        weight4.setText(data.get(21));
        TextView weight5 = (TextView) findViewById(R.id.WeightFive);
        weight5.setText(data.get(22));

        //calculating SEIQoL Score
        Double seiqolScore = (Double.parseDouble(data.get(18)) * Double.parseDouble(data.get(13)))
                + (Double.parseDouble(data.get(19)) * Double.parseDouble(data.get(14)))
                + (Double.parseDouble(data.get(20)) * Double.parseDouble(data.get(15)))
                + (Double.parseDouble(data.get(21)) * Double.parseDouble(data.get(16)))
                + (Double.parseDouble(data.get(22)) * Double.parseDouble(data.get(17)));
        data.set(27, seiqolScore.toString());
        TextView sScore = (TextView) findViewById(R.id.scoreView);
        sScore.setText(data.get(27));

        Button next = (Button) findViewById(R.id.next_7);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
;

                //appendRadioVals();r
                if (rg1.getCheckedRadioButtonId()==R.id.radioButton1) {
                    temp[0] = "Not understood";
                }else if(rg1.getCheckedRadioButtonId()==R.id.radioButton2){
                    temp[0] = "Poor understanding";
                }else if(rg1.getCheckedRadioButtonId()==R.id.radioButton3){
                    temp[0] = "Understood";
                }else{
                    temp[0] = "Error";
                }


                // temp 2 and 3 are switched because the elements were mixed up in the layout
                if (rg2.getCheckedRadioButtonId()==R.id.radioButton4) {
                    temp[2] = "No Boredom";
                }else if(rg2.getCheckedRadioButtonId()==R.id.radioButton5){
                    temp[2] = "Some Boredom";
                }else if(rg2.getCheckedRadioButtonId()==R.id.radioButton6){
                    temp[2] = "A lot Boredom";
                }else{
                    temp[2] = "Error";
                }

                if (rg3.getCheckedRadioButtonId()==R.id.radioButton7) {
                    temp[1] = "No Fatigue";
                }else if(rg3.getCheckedRadioButtonId()==R.id.radioButton8){
                    temp[1] = "Some Fatigue";
                }else if(rg3.getCheckedRadioButtonId()==R.id.radioButton9){
                    temp[1] = "A lot Fatigue";
                }else{
                    temp[1] = "Error";
                }

                if (rg4.getCheckedRadioButtonId()==R.id.radioButton10) {
                    temp[3] = "Definitely Invalid";
                }else if(rg4.getCheckedRadioButtonId()==R.id.radioButton11){
                    temp[3] = "Uncertain Vailidity";
                }else if(rg4.getCheckedRadioButtonId()==R.id.radioButton12){
                    temp[3] = "Definitely Valid";
                }else{
                    temp[3] = "Error";
                }
                data.set(23, temp[0]);
                data.set(24, temp[1]);
                data.set(25, temp[2]);
                data.set(26, temp[3]);

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

    public void appendList(View view) {

    }


    /*Method to merge array and arraylist for inclusion in CSV file*/
    public void appendRadioVals() {
        for (int i = 0; (i < temp.length); i++) {
            //data.set(temp[i]);
            data.set(LIST_INDEX_START + i, temp[i]);
        }
        for (int j = 0; (j < data.size()); j++) {
            Log.w("ArrayList", "j " + j + " element " + data.get(j));
        }
        /*----Add create CSV Code here----*/
    }
}