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
        rGroup.check(R.id.radioButton0);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
        }

//        TextView tv = (TextView) findViewById(R.id.radioButton0);
//        tv.setText(data.get(8));
//        tv = (TextView) findViewById(R.id.radioButton1);
//        tv.setText(data.get(9));
//        tv = (TextView) findViewById(R.id.radioButton2);
//        tv.setText(data.get(10));
//        tv = (TextView) findViewById(R.id.radioButton3);
//        tv.setText(data.get(11));
//        tv = (TextView) findViewById(R.id.radioButton4);
//        tv.setText(data.get(12));

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                // Check which radio button was clicked
                switch(checkedId) {
                    case R.id.radioButton0:
                            myDView.setBarSelected(4);
                        break;
                    case R.id.radioButton1:
                            myDView.setBarSelected(3);
                        break;
                    case R.id.radioButton2:
                            myDView.setBarSelected(2);
                        break;
                    case R.id.radioButton3:
                            myDView.setBarSelected(1);
                        break;
                    default :
                            myDView.setBarSelected(0);
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

        AlertDialog alertDialog = new AlertDialog.Builder(ScreenSamplePie.this).create();
        alertDialog.setTitle("Instructions");
        alertDialog.setMessage("The next step is to show how important the five areas of life you have " +
                "nominated area in relation to each other, by using the pie-chart disk on the screen. " +
                "First the demonstrator will show you an example of how the rating is done.\n" +
                "People often value some areas in life as more important than others.\n" +
                "This disk allows the interviewer to be shown how important each area in your life is by " +
                "giving the more important areas a larger area of the disk, and the less " +
                "important areas a smaller area of the disk. " +
                "First look at this pie chart on the screen. As you can see, there are spaces at the " +
                        "bottom in which there are some sample five important areas of someones life with a colored button " +
                        "beside each of them." +
                        "When one of the colored buttons representing an area of life is selected, the corresponding " +
                        "coloured segment of the piechart is selected and a small tab appears where it can be dragged to resize it " +
                        "The importance of each of the areas of life can be recorded on this pie chart this way by selecting the regments " +
                        "and resizing the space on the disk, the importance of the last segment (green) is the leftover space from" +
                        " the other four areas."

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
