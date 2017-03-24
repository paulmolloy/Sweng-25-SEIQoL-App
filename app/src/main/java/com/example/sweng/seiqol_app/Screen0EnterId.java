package com.example.sweng.seiqol_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Screen0EnterId extends AppCompatActivity {

    String area1,area2;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen0_enter_id);

        Button next = (Button) findViewById(R.id.next_0);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.respondant_id_et);
                area1 = et.getText().toString();
                i = new Intent(Screen0EnterId.this, UserInput.class);
                i.putExtra("AREA1", area1);

                startActivity(i);
            }
        });

        Button back = (Button) findViewById(R.id.back_0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.respondant_id_et);
                EditText interviewer_et = (EditText)findViewById(R.id.interviewer_id_et);
                area1 = et.getText().toString();
                area2 = et.getText().toString();
                i = new Intent(Screen0EnterId.this, MainActivity.class);
                i.putExtra("AREA1", area1);
                i.putExtra("AREA2", area2);
                startActivity(i);
            }
        });






        AlertDialog alertDialog = new AlertDialog.Builder(Screen0EnterId.this).create();
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
