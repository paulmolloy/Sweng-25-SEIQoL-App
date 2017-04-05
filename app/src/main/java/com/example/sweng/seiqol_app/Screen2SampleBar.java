package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Screen2SampleBar extends AppCompatActivity {
    TextView myTv;
    DraggableBarChart myDView;
    //RadioGroup rGroup, rGroup1;
    RadioGroup rg1, rg2;
    Intent i;
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2_sample_bar);
        myTv = (TextView) findViewById(R.id.textView2);
        myDView = (DraggableBarChart) findViewById(R.id.dView);
        //rGroup = (RadioGroup) findViewById(R.id.rBG0);
        //rGroup1 = (RadioGroup) findViewById(R.id.rBG1);
        rg1 = (RadioGroup) findViewById(R.id.rBG0);
        RadioButton r1 = (RadioButton) findViewById(R.id.radioButton0);
        r1.requestFocus();
        rg2 = (RadioGroup) findViewById(R.id.rBG1);
        rg1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        rg2.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);

        //rGroup.check(R.id.radioButton2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
        }


        Button next = (Button) findViewById(R.id.next_1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(Screen2SampleBar.this, RectTesting.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.back_1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Screen2SampleBar.this, UserInput.class);
                i.putExtra("DATA", data);
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


    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rg2.clearCheck(); // clear the second RadioGroup!
                rg2.setOnCheckedChangeListener(listener2); //reset the listener
                Log.e("XXX2", "do the work");

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

                    }
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);
                Log.e("XXX2", "do the work");
                switch (checkedId) {
                        case R.id.radioButton3:
                            myDView.setBarSelected(3);
                            break;
                        default:
                            myDView.setBarSelected(4);
                            break;
                    }
            }
        }
    };





}
