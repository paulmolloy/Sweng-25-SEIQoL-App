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
        tv.setText(data.get(3));
        tv = (TextView) findViewById(R.id.radioButton1);
        tv.setText(data.get(4));
        tv = (TextView) findViewById(R.id.radioButton2);
        tv.setText(data.get(5));
        tv = (TextView) findViewById(R.id.radioButton3);
        tv.setText(data.get(6));
        tv = (TextView) findViewById(R.id.radioButton4);
        tv.setText(data.get(7));

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
                data.add(13, Double.toString(temp[0]));
                data.add(14, Double.toString(temp[1]));
                data.add(15, Double.toString(temp[2]));
                data.add(16, Double.toString(temp[3]));
                data.add(17 , Double.toString(temp[4]));
                i = new Intent(PieTesting.this, Screen7InterviewerRecord.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.back_3 );
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(PieTesting.this, RectTesting.class);
                startActivity(i);
            }
        });



        AlertDialog alertDialog = new AlertDialog.Builder(PieTesting.this).create();
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
