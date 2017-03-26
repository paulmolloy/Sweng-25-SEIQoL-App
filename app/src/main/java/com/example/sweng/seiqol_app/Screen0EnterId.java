package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class Screen0EnterId extends AppCompatActivity {
    ArrayList<String> data = new ArrayList<>();
    String respondent,interviewer;
    Intent i;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen0_enter_id);

        Button begin = (Button) findViewById(R.id.begin_0);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.respondant_id_et);
                respondent = et.getText().toString();
                et = (EditText)findViewById(R.id.interviewer_id_et);
                interviewer = et.getText().toString();
                Calendar calendar = Calendar.getInstance();
                time = calendar.getTimeInMillis();
                data.add(0, respondent);
                data.add(1, interviewer);
                data.add(2, Long.toString(time));
                i = new Intent(Screen0EnterId.this, UserInput.class);
                i.putExtra("DATA", data);

                startActivity(i);
            }
        });

    }



}
