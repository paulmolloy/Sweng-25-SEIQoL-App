package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RectTesting extends AppCompatActivity {
    TextView myTv;
    DraggableBarChart myDView;
    RadioGroup rGroup;
    ArrayList<String> data = new ArrayList<>();
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rect_testing);
        myTv = (TextView) findViewById(R.id.textView2);
        myDView = (DraggableBarChart) findViewById(R.id.dView);
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
                //for(int i=0;i<10;i++)System.out.println("!!!!!!!!!!!!!!!is this visible!!!!!!!");


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
        Button next = (Button) findViewById(R.id.next_2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double[] temp = myDView.getAllBarValues();
                data.set(13, Double.toString(temp[0]));
                data.set(14, Double.toString(temp[1]));
                data.set(15, Double.toString(temp[2]));
                data.set(16, Double.toString(temp[3]));
                data.set(17, Double.toString(temp[4]));
                i = new Intent(RectTesting.this, PieTesting.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.back_2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(RectTesting.this, Screen2SampleBar.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(RectTesting.this).create();
        alertDialog.setTitle("Instructions");
        alertDialog.setMessage("Now I want you to rate the five most important areas of your life, as you see\n" +
                "presented here (indicate). Firstly, draw a bar which represents how you\n" +
                "would rate yourself on each of these areas at the moment. As in the example\n" +
                "I’ve just shown you, the nearer you draw the bar to the bottom line, the\n" +
                "poorer you are rating that area of your life and the nearer you draw it to the\n" +
                "top, the better your rating of that area of your life."
        );
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();

    }
}