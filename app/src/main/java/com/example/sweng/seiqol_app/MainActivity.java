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

    Intent i;
    Button drawB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(MainActivity.this, RectTesting.class);
        Button drawViewB = (Button) findViewById(R.id.draw_b);
        drawViewB.setOnClickListener(this);



//// in this example, a LineChart is initialized from xml
//        LineChart chart = (LineChart) findViewById(R.id.chart);
//        YourData[] dataObjects = {new YourData(1,2),new YourData(3,4),new YourData(7,7)};
//
//        List<Entry> entries = new ArrayList<Entry>();
//
//        for (YourData data : dataObjects) {
//
//            // turn your data into Entry objects
//            entries.add(new Entry(data.getValueX(), data.getValueY()));
//        }
//
//        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
//        dataSet.setColor(30);
//        dataSet.setValueTextColor(200); // styling, ...
//        LineData lineData = new LineData(dataSet);
//        chart.setData(lineData);
//        chart.invalidate(); // refresh





        BarChart chart = (BarChart) findViewById(R.id.chart);
        YourData[] dataObjects = {new YourData(1,2),new YourData(3,4),new YourData(7,7)};

        List<BarEntry> entries = new ArrayList<BarEntry>();

        for (YourData data : dataObjects) {

            // turn your data into Entry objects
            entries.add(new BarEntry(data.getValueX(), data.getValueY()));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Label"); // add entries to dataset


        dataSet.setValueTextColor(1); // styling, ...
        BarData barData = new BarData(dataSet);
        chart.setData(barData);
        chart.invalidate(); // refresh





    }



    public class YourData{
        private int x;
        private int y;
        public YourData(int x, int y){
            this.x=x;
            this.y=y;
        }
        public int getValueX(){
            return x;
        }
        public int getValueY(){
            return y;
        }


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.draw_b:
                startActivity(i);
                break;
        }
    }
}
