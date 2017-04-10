package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ScreenSamplePie extends AppCompatActivity {
    TextView myTv;
    DraggablePieChart myDView;
    RadioGroup rGroup;
    ArrayList<String> data = new ArrayList<>();
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_pie);
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

//                data.set(18, Double.toString(roundD(temp[0],4)));
//                data.set(19, Double.toString(roundD(temp[1],4)));
//                data.set(20, Double.toString(roundD(temp[2],4)));
//                data.set(21, Double.toString(roundD(temp[3],4)));
//                data.set(22 , Double.toString(roundD(temp[4],4)));
//                data.set(18, Double.toString(temp[0]));
//                data.set(19, Double.toString(temp[1]));
//                data.set(20, Double.toString(temp[2]));
//                data.set(21, Double.toString(temp[3]));
//                data.set(22 , Double.toString(temp[4]));
                i = new Intent(ScreenSamplePie.this, PieTesting.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.back_3 );
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(ScreenSamplePie.this, RectTesting.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });




    }
    public static double roundD(double num, int places){
        num = Math.round(num * (10*places));
        num = num/(10*places);
        return num;
    }
}
