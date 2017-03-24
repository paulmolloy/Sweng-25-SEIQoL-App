package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RectTesting extends AppCompatActivity {
    TextView myTv;
    DraggableBarChart myDView;
    RadioGroup rGroup;
    private RadioButton rb0, rb1, rb2,rb3,rb4;
    String area1, area2, area3, area4, area5;

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
            area1 = extras.getString("AREA1");
            area2 = extras.getString("AREA2");
            area3 = extras.getString("AREA3");
            area4 = extras.getString("AREA4");
            area5 = extras.getString("AREA5");
        }
        TextView tv1 = (TextView) findViewById(R.id.radioButton0);
        tv1.setText(area1);
        TextView tv2 = (TextView) findViewById(R.id.radioButton1);
        tv2.setText(area2);
        TextView tv3 = (TextView) findViewById(R.id.radioButton2);
        tv3.setText(area3);
        TextView tv4 = (TextView) findViewById(R.id.radioButton3);
        tv4.setText(area4);
        TextView tv5 = (TextView) findViewById(R.id.radioButton4);
        tv5.setText(area5);


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


        AlertDialog alertDialog = new AlertDialog.Builder(RectTesting.this).create();
        alertDialog.setTitle("Instructions");
        alertDialog.setMessage("Now that you have named the five most important areas in your life, I am\n" +
                "going to ask you to rate how each of these areas are for you at the moment.\n" +
                "First I will show you an example of how the rating is done\n" +
                "First look at this box (indicate). As you can see, there are spaces at the\n" +
                "bottom in which I can write the five important areas of my life (indicate), and\n" +
                "there is a scale along the left hand side (indicate). The scale ranges from\n" +
                "‘worst possible’ on the bottom to ‘best possible’ on the top, and passes\n" +
                "through levels such as ‘very bad’ – ‘bad’ – ‘neither good nor bad’ – ‘good’ –\n" +
                "and ‘very good’ between the two extremes."
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