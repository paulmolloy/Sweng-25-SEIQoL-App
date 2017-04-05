package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        data.add(0,"");//id
        data.add(1,"");//name
        data.add(2,"");//time
        data.add(3,"");//desc
        data.add(4,"");
        data.add(5,"");
        data.add(6,"");
        data.add(7,"");
        data.add(8,"");//cues
        data.add(9,"");
        data.add(10,"");
        data.add(11,"");
        data.add(12,"");
        data.add(13,"");//bar
        data.add(14,"");
        data.add(15,"");
        data.add(16,"");
        data.add(17,"");
        data.add(18,"");//pie
        data.add(19,"");
        data.add(20,"");
        data.add(21,"");
        data.add(22,"");
        data.add(23,"");//assess
        data.add(24,"");

        data.add(25,"");
        data.add(26,"");
        data.add(27,"");//seiqol score

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




                if(interviewer.length() > 1 && respondent.length() > 1) {
                    data.set(0, respondent);
                    data.set(1, interviewer);
                    data.set(2, Long.toString(time));
                    i = new Intent(Screen0EnterId.this, UserInput.class);
                    i.putExtra("DATA", data);

                    startActivity(i);
                }
                else
                {
                    displayError(v);
                }
            }
        });




        AlertDialog alertDialog = new AlertDialog.Builder(Screen0EnterId.this).create();
        alertDialog.setTitle("Instructions");
        alertDialog.setMessage("Please enter the name of the interviewer and then the ID of the respondant to begin the SEIQol evaluation."
        );
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void displayError(View view){
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String dMessage = "Please enter a valid name and ID.";
        builder.setMessage(dMessage);
        builder.setTitle("Invalid input");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
