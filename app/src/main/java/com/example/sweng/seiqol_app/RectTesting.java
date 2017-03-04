package com.example.sweng.seiqol_app;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rect_testing);
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

    }
}
