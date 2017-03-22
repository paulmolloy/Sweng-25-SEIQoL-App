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
        setContentView(R.layout.activity_user_input);
        Button cb1 = (Button) findViewById(R.id.Confirm1);
        Button cb2 = (Button) findViewById(R.id.Confirm2);
        Button cb3 = (Button) findViewById(R.id.Confirm3);
        Button cb4 = (Button) findViewById(R.id.Confirm4);
        Button cb5 = (Button) findViewById(R.id.Confirm5);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.FirstArea);
                // TextView tv1 = (TextView) findViewById(R.id.textView);
                // tv1.setText(et1.getText().toString());
                area1 = et.getText().toString();
                et.setEnabled(false);
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.SecondArea);
                // TextView tv1 = (TextView) findViewById(R.id.textView);
                // tv1.setText(et1.getText().toString());
                area2 = et.getText().toString();
                et.setEnabled(false);
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.ThirdArea);
                // TextView tv1 = (TextView) findViewById(R.id.textView);
                // tv1.setText(et1.getText().toString());
                area3 = et.getText().toString();
                et.setEnabled(false);
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.FourthArea);
                // TextView tv1 = (TextView) findViewById(R.id.textView);
                // tv1.setText(et1.getText().toString());
                area4 = et.getText().toString();
                et.setEnabled(false);
            }
        });
        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.FifthArea);
                // TextView tv1 = (TextView) findViewById(R.id.textView);
                // tv1.setText(et1.getText().toString());
                area5 = et.getText().toString();
                et.setEnabled(false);
            }
        });


        Button next = (Button) findViewById(R.id.Next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(UserInput.this, RectTesting.class);
                i.putExtra("AREA1", area1);
                i.putExtra("AREA2", area2);
                i.putExtra("AREA3", area3);
                i.putExtra("AREA4", area4);
                i.putExtra("AREA5", area5);
                startActivity(i);
            }
        });

    }


}