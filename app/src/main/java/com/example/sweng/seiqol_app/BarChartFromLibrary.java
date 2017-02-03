package com.example.sweng.seiqol_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class BarChartFromLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_from_library);

////////Line chart is from example on MPAndroidChart library
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
        BarChartFromLibrary.YourData[] dataObjects = {new BarChartFromLibrary.YourData(1,2),new BarChartFromLibrary.YourData(3,4),new BarChartFromLibrary.YourData(7,7)};

        List<BarEntry> entries = new ArrayList<BarEntry>();

        for (BarChartFromLibrary.YourData data : dataObjects) {

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

}

