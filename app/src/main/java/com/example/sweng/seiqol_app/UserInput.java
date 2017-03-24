package com.example.sweng.seiqol_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserInput extends AppCompatActivity  {
    String area1;
    String area2;
    String area3;
    String area4;
    String area5;
    Intent i;
    Intent j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1_cues);


        Button next = (Button) findViewById(R.id.Next);
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
                i = new Intent(UserInput.this, RectTesting.class);
                i.putExtra("AREA1", area1);
                i.putExtra("AREA2", area2);
                i.putExtra("AREA3", area3);
                i.putExtra("AREA4", area4);
                i.putExtra("AREA5", area5);
                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(UserInput.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


}