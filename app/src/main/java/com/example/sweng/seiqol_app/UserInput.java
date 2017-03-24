package com.example.sweng.seiqol_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class UserInput extends AppCompatActivity  {
    ArrayList<String> data = new ArrayList<>();
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1_cues);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getStringArrayList("DATA");
        }

        Button next = (Button) findViewById(R.id.next_0);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(3, ((EditText)findViewById(R.id.FirstArea)).getText().toString());
                data.add(4, ((EditText)findViewById(R.id.SecondArea)).getText().toString());
                data.add(5, ((EditText)findViewById(R.id.ThirdArea)).getText().toString());
                data.add(6, ((EditText)findViewById(R.id.FourthArea)).getText().toString());
                data.add(7, ((EditText)findViewById(R.id.FifthArea)).getText().toString());

                i = new Intent(UserInput.this, Screen2SampleBar.class);
                i.putExtra("DATA", data);
                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.back_0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(UserInput.this, Screen0EnterId.class);
                startActivity(i);
            }
        });

    }


}