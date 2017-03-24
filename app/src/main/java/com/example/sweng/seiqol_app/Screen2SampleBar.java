package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Screen2SampleBar extends AppCompatActivity {
    TextView myTv;
    DraggableBarChart myDView;
    RadioGroup rGroup;
    private RadioButton rb0, rb1, rb2,rb3,rb4;
    String area1, area2, area3, area4, area5;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2_sample_bar);
        myTv = (TextView) findViewById(R.id.textView2);
        myDView = (DraggableBarChart) findViewById(R.id.dView);
        rGroup = (RadioGroup) findViewById(R.id.rBG0);
        rGroup.check(R.id.radioButton2);




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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

        }

        Button next = (Button) findViewById(R.id.next_2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.FirstArea);
                area1 = et.getText().toString();
                et = (EditText)findViewById(R.id.SecondArea);
                area2 = et.getText().toString();
                et = (EditText)findViewById(R.id.ThirdArea);
                area3 = et.getText().toString();
                et = (EditText)findViewById(R.id.FourthArea);
                area4 = et.getText().toString();
                et = (EditText)findViewById(R.id.FifthArea);
                area5 = et.getText().toString();
                i = new Intent(Screen2SampleBar.this, RectTesting.class);
                i.putExtra("AREA1", area1);
                i.putExtra("AREA2", area2);
                i.putExtra("AREA3", area3);
                i.putExtra("AREA4", area4);
                i.putExtra("AREA5", area5);
                startActivity(i);
            }
        });


        AlertDialog alertDialog = new AlertDialog.Builder(Screen2SampleBar.this).create();
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
