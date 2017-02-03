package com.example.sweng.seiqol_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Intent i,j;
    Button drawB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(MainActivity.this, RectTesting.class);
        Button drawViewB = (Button) findViewById(R.id.draw_b);
        drawViewB.setOnClickListener(this);

        j = new Intent(MainActivity.this, BarChartFromLibrary.class);
        Button libBarChartB = (Button) findViewById(R.id.lib_b);
        libBarChartB.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.draw_b:
                startActivity(i);
                break;
            case R.id.lib_b:
                startActivity(j);
                break;
            default:

        }
    }
}
