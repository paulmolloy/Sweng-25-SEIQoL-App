package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PieTesting extends AppCompatActivity {
    TextView myTv;
    DraggablePieChart myDView;
    RadioGroup rGroup;
    ArrayList<String> data = new ArrayList<>();
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_testing);
        myTv = (TextView) findViewById(R.id.textView2);
        myDView = (DraggablePieChart) findViewById(R.id.pieView);
        rGroup = (RadioGroup) findViewById(R.id.rBG0);
        rGroup.check(R.id.radioButton2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
        }

        TextView tv = (TextView) findViewById(R.id.radioButton0);
        tv.setText(data.get(8));
        tv = (TextView) findViewById(R.id.radioButton1);
        tv.setText(data.get(9));
        tv = (TextView) findViewById(R.id.radioButton2);
        tv.setText(data.get(10));
        tv = (TextView) findViewById(R.id.radioButton3);
        tv.setText(data.get(11));
        tv = (TextView) findViewById(R.id.radioButton4);
        tv.setText(data.get(12));

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                // Check which radio button was clicked
                switch(checkedId) {
                    case R.id.radioButton0:
                            myDView.setBarSelected(0);
                        break;
                    case R.id.radioButton1:
                            myDView.setBarSelected(1);
                        break;
                    case R.id.radioButton2:
                            myDView.setBarSelected(2);
                        break;
                    case R.id.radioButton3:
                            myDView.setBarSelected(3);
                        break;
                    default :
                            myDView.setBarSelected(4);
                        break;
                }


            }
        });

        Button next = (Button) findViewById(R.id.next_3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double[] temp = myDView.getAllBarValues();
//rounding 2 four places
                DecimalFormat df = new DecimalFormat("#.####");
                data.set(18, df.format(temp[0]));
                data.set(19, df.format(temp[1]));
                data.set(20, df.format(temp[2]));
                data.set(21, df.format(temp[3]));
                data.set(22 , df.format(temp[4]));
//                data.set(18, Double.toString(temp[0]));
//                data.set(19, Double.toString(temp[1]));
//                data.set(20, Double.toString(temp[2]));
//                data.set(21, Double.toString(temp[3]));
//                data.set(22 , Double.toString(temp[4]));
                i = new Intent(PieTesting.this, Screen7InterviewerRecord.class);
                i.putExtra("DATA", data);
                startActivity(i);

            }
        });

        Button back = (Button) findViewById(R.id.back_3 );
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(PieTesting.this, ScreenSamplePie.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });



        AlertDialog alertDialog = new AlertDialog.Builder(PieTesting.this).create();
        alertDialog.setTitle("Instructions");
        alertDialog.setMessage("I would like you to show me how important the five areas of life you have\n" +
                "nominated area in relation to each other, by using this disk (indicate SEIQoL-\n" +
                "DW). People often value some areas in life as more important than others.\n" +
                "This disk allows you to show me how important each area in your life is by\n" +
                "giving the more important areas a larger area of the disk, and the less\n" +
                "important areas a smaller area of the disk. In my life, for example, X (name\n" +
                "cue not already chosen by respondent) is about this important (manipulate disk so\n" +
                "that X represents 30% of space available). X 2 however is less important than X,\n" +
                "so it has only this much of the pie (manipulate disk so that X 2 represents 20% of\n" +
                "the space available). X 3 on the other hand is more important than X, so it has\n" +
                "this much of the pie (manipulate DWP so that X 3 represents 40% of space\n" +
                "available). Finally, X 4 and X 5 are the least important areas of life for me, and\n" +
                "I value them about the same (manipulate disk so that X 4 and X 5 represent 5% each\n" +
                "of space available). Now thinking about the five areas of life you have\n" +
                "mentioned (write the name of each cue along the cut edge of one of the 5 coloured\n" +
                "disks with a non-permanent marker [disks may also be marked with stick-on ‘post-\n" +
                "it’ labels indicating the cues if preferred]). I would like you to show me how\n" +
                "important these areas are in relation to each other by moving the disks\n" +
                "around until their relative size represents your view of their importance"
        );
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }
    public static double roundD(double num, int places){
        num = Math.round(num * (10*places));
        num = num/(10*places);
        return num;
    }
}
